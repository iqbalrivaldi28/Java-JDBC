package com.enigma.product;

import com.enigma.config.DBConnector;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public void insertProduct(String prouductName, Integer productPrice, Integer stock) throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, prouductName);
        preparedStatement.setInt(2, productPrice);
        preparedStatement.setInt(3, stock);
        preparedStatement.executeUpdate();

        System.out.println("INSERT SUCCESS");
        preparedStatement.close();
        connection.close();

    }


    public void updateProduct(Integer productId, String name) throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "UPDATE products SET name = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, productId);
        preparedStatement.executeUpdate();

        System.out.println("Update SUCCESS");
        preparedStatement.close();
        connection.close();
    }

    public void deleteProduct(Integer productId) throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "DELETE FROM products where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productId);
        preparedStatement.executeUpdate();

        System.out.println("Delete SUCCESS");
        preparedStatement.close();
        connection.close();
    }



    public List<Product> getData () throws SQLException{

        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "SELECT * FROM products";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Aray List
        List<Product> productList = new ArrayList<>();

        while (resultSet.next()) {
            int productId = resultSet.getInt("id");
            String productName = resultSet.getString("name");
            int productPrice = resultSet.getInt("price");
            int productStock = resultSet.getInt("stock");

            // Membuat objek Product dan menambahkannya ke dalam ArrayList
            Product product = new Product(productId, productName, productPrice, productStock);
            productList.add(product);
        }
        System.out.println("Get Data SUCCESS uhuy");

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return productList;
    }
}
