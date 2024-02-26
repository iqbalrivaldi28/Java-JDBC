package com.enigma.product;

import com.enigma.config.DBConnector;

import java.sql.*;
import java.util.StringTokenizer;

public class TransactionDAO {
    private Connection connection;

    public void purchasingProduct(Integer productID, Integer quantity, Integer customerID) {
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();


        try {
            // begin transaction
            connection.setAutoCommit(false);

            //transaction
            String sqlSelect = "SELECT price FROM products WHERE id = ?";
            PreparedStatement statementPrice = connection.prepareStatement(sqlSelect);
            statementPrice.setInt(1, productID);
            ResultSet resultSetPrice = statementPrice.executeQuery();
            resultSetPrice.next();
            int price = resultSetPrice.getInt("price");

            statementPrice.close();
            resultSetPrice.close();

            // Challange
            String sqlSelectCustomer = "SELECT * FROM ms_customer WHERE customer_id = ?";
            PreparedStatement statementCustomer = connection.prepareStatement(sqlSelectCustomer);
            statementCustomer.setInt(1, customerID);
            ResultSet resultSetCustomer = statementCustomer.executeQuery();
            resultSetCustomer.next();
            int customer = resultSetCustomer.getInt("customer_id");
            System.out.println(customer);

            statementCustomer.close();
            resultSetCustomer.close();

            // Challange Insert To Customer
            String sqlInsertCustomer = "INSERT INTO ms_customer (name, address, birth_date, status, phone) VALUES (?, ?, ? ,?)";
            PreparedStatement statementInsertCustomer = connection.prepareStatement(sqlInsertCustomer);
            statementInsertCustomer.setString(1, "Raisa");
            statementInsertCustomer.setString(2, "Bandung");
            statementInsertCustomer.setString(3, "1990-06-06");
            statementInsertCustomer.setInt(4, 1234754745);
            statementInsertCustomer.executeUpdate();

            statementCustomer.close();


            String sqlInsert = "INSERT INTO trx_purchase(product_id, product_price, quantity, customer_id) VALUES(?, ?, ?, ?)";
            PreparedStatement statementInsert = connection.prepareStatement(sqlInsert);
            statementInsert.setInt(1, productID);
            statementInsert.setInt(2, price);
            statementInsert.setInt(3, quantity);
            statementInsert.setInt(4, customer);
            statementInsert.executeUpdate();

            statementInsert.close();

            String sqlSelectStock = "SELECT stock FROM products WHERE id = ?";
            PreparedStatement statementStock = connection.prepareStatement(sqlSelectStock);
            statementStock.setInt(1, productID);
            ResultSet resultSetStock = statementStock.executeQuery();
            resultSetStock.next();
            int stock = resultSetStock.getInt("stock");

            String sqlUpdate = "UPDATE products SET stock = ? WHERE id = ?";
            PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdate);
            statementUpdate.setInt(1, stock - quantity);
            statementUpdate.setInt(2, productID);
            statementUpdate.executeUpdate();
            statementUpdate.close();



            // commit
            connection.commit();
            connection.close();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }


    }
}





