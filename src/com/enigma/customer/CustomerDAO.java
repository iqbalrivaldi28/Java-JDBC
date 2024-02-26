package com.enigma.customer;

import com.enigma.config.DBConnector;
import com.enigma.product.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private Connection connection;

    public void insertCustomer(String name, String address, String birth_date, String status, Integer phone) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "INSERT INTO ms_customer (name, address, birth_date, status, phone) VALUES (?, ?, ?,?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,address);
        preparedStatement.setString(3,birth_date);
        preparedStatement.setString(4,status);
        preparedStatement.setInt(5,phone);
        preparedStatement.executeUpdate();

        System.out.println("INSERT SUCCESS");
        preparedStatement.close();
        connection.close();
    }

    public void updateCustomer(Integer customerId, String name) throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "UPDATE ms_customer SET name = ? where customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, customerId);
        preparedStatement.executeUpdate();

        System.out.println("Update SUCCESS");
        preparedStatement.close();
        connection.close();
    }

    public void deleteCustomer(Integer customerID) throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "DELETE FROM ms_customer where customer_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customerID);
        preparedStatement.executeUpdate();

        System.out.println("Delete SUCCESS");
        preparedStatement.close();
        connection.close();
    }

    public List<Customer> getData () throws SQLException{
        DBConnector dbConnector = new DBConnector();
        connection = dbConnector.connect();

        String sql = "SELECT * FROM ms_customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // Array List
        List<Customer> customerList  = new ArrayList<>();

        while (resultSet.next()){
            int customerId = resultSet.getInt("customer_id");
            String name = resultSet.getString("name");
            String address = resultSet.getString("address");
            String birth_date = resultSet.getString("birth_date");
            String status = resultSet.getString("status");
            int phone = resultSet.getInt("phone");

            // Membuat objek Product dan menambahkannya ke dalam ArrayList
            Customer customer = new Customer(customerId, name, address, birth_date, status, phone);
            customerList.add(customer);
        }
        System.out.println("Get data Berhail bray");

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return customerList;
    }
}
