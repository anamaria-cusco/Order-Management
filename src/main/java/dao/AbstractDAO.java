package dao;


import dao.connection.ConnectionFactory;
import dao.sqlUtil.SqlDataTypeResolver;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     *
     * @param field specifica coloana dupa care se face selectia
     * @return String - query-ul de SELECT
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" * ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());

        if(field!=null) {
            sb.append(" WHERE ").append(field).append("=?");
        }
        return sb.toString();
    }

    /**
     *
     * @param field specifica cheia primara a tabelului in care se face inserarea
     * @param properties - un set de perechi proprietare-valoare ale obiectului
     * @return String - query-ul de INSERT
     */
    private String createInsertQuery( String field,Map<String, Object> properties) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()).append("(");
        properties.keySet().forEach(k  -> { if (!k.equals(field)) sb.append(k).append(", ");});
        sb.delete(sb.length() - 2, sb.length());
        sb.append(")");
        sb.append(" VALUES (");
        for (String key : properties.keySet()) {
            if(key.equals(field)) continue;
            Object value = properties.get(key);

            if (SqlDataTypeResolver.isQuotesNeeded(value)) {
                sb.append(String.format("'%s', ", value));
            } else {
                sb.append(String.format("%s, ", value));
            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" )");

        return sb.toString();
    }

    /**
     *
     * @param field specifica cheia primara a tabelului in care se face actualizarea
     * @param properties -- un set de perechi proprietare-valoare ale obiectului
     * @return String - query-ul de UPDATE
     */
    private String createUpdateQuery(String field, Map<String, Object> properties ) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        for (String key:properties.keySet()) {
            if (key.equals(field)) { continue; }
            Object value = properties.get(key);
            if (SqlDataTypeResolver.isQuotesNeeded(value)) {
                sb.append(String.format("%s = '%s', ", key, value));
            } else {
                sb.append(String.format("%s = %s, ", key, value));
            }
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        if (SqlDataTypeResolver.isQuotesNeeded(properties.get(field))) {
            sb.append(String.format("WHERE %s = '%s'",field, properties.get(field)));
        } else {
            sb.append(String.format("WHERE %s = %s",field,properties.get(field)));
        }

        return sb.toString();
    }

    /**
     *
     * @param field specifica cheia primara a tabelului in care se face stergerea
     * @return  String - query-ul de DELETE
     */
    private String createDeleteQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }

    /**
     * Selecteaza toate obiectele dintr-un tabel din baza de date
     * @return -lista de obiecte din tabel
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query= createSelectQuery(null);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;

    }

    /**
     * Selecteaza un obiect specific din baza de date care are valoarea din campul ID = id
     * @param id - parametrul dupa care se face cautarea
     * @return - obiectul rezultat in urma cautarii, null- in caz ca nu se gaseste
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     *
     * @param resultSet - setul de rezultate obtinut in urma interogarii
     * @return -lista de obiecte covertita din ResultSet
     */
    List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();

        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | IntrospectionException
                | InvocationTargetException | IllegalArgumentException | SecurityException | SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda de inserarea a unui nou obiect in baza de date
     * @param t -obiectul de inserat
     * @return - id -ul generat automat de catre baza de date la inserarea unui nou element
     */
    public int insert(T t) {
        int id=-1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Map<String,Object> properties= retrieveFieldsMap(t);
        String query = createInsertQuery("id",properties);
        System.out.println("Insert Query:"+query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            statement.execute();
            resultSet=statement.getGeneratedKeys();
            if(resultSet.next()){
            id=resultSet.getInt(1);
            }

            return id;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return id;
    }

    /**
     * Metoda de actualizare a unui obiect
     * @param t -obiectul de actualizat
     * @return -true sau false in functie de succesul/esecul operatiei
     */
    public boolean update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int changedRows;
        Map<String,Object> properties= retrieveFieldsMap(t);
        String query = createUpdateQuery("id",properties);
        System.out.println("Update Query:"+query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            changedRows = statement.executeUpdate(query);

            return changedRows>0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    /**
     * Metoda de stergere a unui obiect din baza de date
     * @param id - id-ul obiectului de sters
     * @return : true sau fals in caz de succes/esec
     */
    public boolean delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int deletedRows;
        String query = createDeleteQuery("id");
        System.out.println("Delete Query:"+query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            deletedRows = statement.executeUpdate();

            return deletedRows>0;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {

            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return false;
    }

    /**
     * Extrage proprietatile unui obiect impreuna cu valorile lui
     * @param t -un obiect din model
     * @return - perechi String-Object ce reprezinta field-urile obiectului si valorile lor
     */
    public Map<String, Object> retrieveFieldsMap(T t) {
        Map<String, Object> fieldsMap = new HashMap<>();
        Field[] fields = type.getDeclaredFields();
        Arrays.asList(fields).forEach(f -> { f.setAccessible(true);
            try {
                fieldsMap.put(f.getName(), f.get(t));
            } catch (Exception e) {
                LOGGER.log(Level.WARNING,type.getName()+"Problems in reading object properties!" + e.getMessage());
            }});

        return fieldsMap;
    }
}
