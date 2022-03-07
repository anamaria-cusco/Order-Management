package dao;


import dao.connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

public class ProductDAO extends AbstractDAO<Product>{
    /**
     * Cuatarea unui produs dupa nume
     * @param name -numele produsului
     * @return -produsul gasit sau null in caz contrar
     */
    public Product findProductByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query="SELECT * FROM product WHERE name=?";

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,name);

            resultSet = statement.executeQuery();

            if(createObjects(resultSet)!=null && createObjects(resultSet).size()!=0) {
                return createObjects(resultSet).get(0);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findProductByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Cautarea produselor a caror nume incep cu un anumit pattern
     * @param pattern - sablonul dupa care se cauta
     * @return -lista de produse
     */
    public List<Product> findProductsByPattern(String pattern){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query="SELECT * FROM product WHERE name LIKE '" +pattern+ "%'";

        try {
            System.out.println(query);
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findProductByFirstLetter " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

}
