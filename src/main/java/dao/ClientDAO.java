package dao;

import dao.connection.ConnectionFactory;
import model.Client;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;

public class ClientDAO extends AbstractDAO<Client> {

    /**
     * Cautarea unui client dupa anumite filed-uri specifice
     * @param c -obiectul de tip Client dupa care se face cautarea
     * @return -returneaza un alt obiect gasit sau null in caz contrar
     */
        public Client findClientBySpecifiedFields(Client c){

            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            String query = "SELECT * FROM client WHERE firstName=? AND lastName=? AND address=? AND phone_number=? AND email=?;";
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                statement.setString(1, c.getFirstName());
                statement.setString(2, c.getLastName());
                statement.setString(3, c.getAddress());
                statement.setString(4, c.getPhone_number());
                statement.setString(5, c.getEmail());
                resultSet = statement.executeQuery();
                if(resultSet.next()){
                    System.out.println(resultSet.getString(2));
                }
                if(createObjects(resultSet)!=null && createObjects(resultSet).size()!=0) {
                    return createObjects(resultSet).get(0);
                }
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ClientDAO:findClientBySpecifiedFields " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }

    /**
     * Cautarea unui client dupa nume
     * @param name - numele clientului
     * @return - lista clientilor care au firstName sau lastName egal cu nume
     */
        public List<Client> findClientByName(String name){

            String query;
            boolean second=false;
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            name=name.trim();
            String[] names=name.split(" ");
            if (names.length==2){
                second =true;
                query="SELECT * FROM client WHERE firstName=? AND lastName =?  OR  firstName=? AND lastName =? ";
            }
            else {
                query = "SELECT * FROM client WHERE firstName=? OR lastName =?";
            }
            try {
                System.out.println(query);
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);
                if(second) {
                    statement.setString(1, names[0]);
                    statement.setString(2, names[1]);
                    statement.setString(3, names[1]);
                    statement.setString(4, names[0]);
                }else {
                    statement.setString(1, name);
                    statement.setString(2, name);
                }

                resultSet = statement.executeQuery();

                return createObjects(resultSet);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "ClientDAO:findClientByName " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
            return null;
        }


}
