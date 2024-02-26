package com.enigma;

import com.enigma.customer.Customer;
import com.enigma.customer.CustomerDAO;
import com.enigma.product.Product;
import com.enigma.product.ProductDAO;
import com.enigma.product.TransactionDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Belajar JDBC");

//        Connection conn = null;
//
//        // ToDo
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_enigma",
//                    "postgres", "postgres123");
//
//            System.out.println(conn);
//
//
//            // Retreving Result Set
//            Statement statement = conn.createStatement();
//
//            String sql = "SELECT * FROM student";
//            ResultSet resultSet =  statement.executeQuery(sql);
//
//            //System.out.println(resultSet.next());
//            //System.out.println(resultSet.getString(2));
//
//            // Kita ubah pake perulangan
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("id") + "|" + resultSet.getString("name"));
//            }
//
//
//            // PREPARATION STATE
//            String sql2 = "SELECT * FROM student WHERE age = ? AND email = ?";
//            PreparedStatement preparedStatement = conn.prepareStatement(sql2);
//
//            preparedStatement.setInt(1, 20);
//            preparedStatement.setString(2, "2OZz0@example.com");
//
//            // execute
//            resultSet =  preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("id") + "|" + resultSet.getString("name"));
//            }
//
//
//
//            // Insert Data
//            String sql3 = "INSERT INTO student (name, age, address, email) VALUES (?,?,?,?) ";
//            PreparedStatement preparedStatement1  =  conn.prepareStatement(sql3);
//
//            preparedStatement1.setString(1,"Nasywa");
//            preparedStatement1.setInt(2,17);
//            preparedStatement1.setString(3,"Jakarta");
//            preparedStatement1.setString(4,"nasywa@gmail.com");
//
//            //resultSet = preparedStatement1.executeQuery();
//            int insertData = preparedStatement1.executeUpdate();
//            System.out.println("Baris di insert:  " + insertData);
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("id") + "|" + resultSet.getString("name"));
//            }
//
//
//
//            // Update Data
//            String sql4 = "UPDATE student set age = ?, address = ?, email = ? where name = ?";
//            PreparedStatement preparedStatement2 = conn.prepareStatement(sql4);
//
//            preparedStatement2.setInt(1, 17);
//            preparedStatement2.setString(2, "Bandung");
//            preparedStatement2.setString(3, "Naisa@gmail.com");
//            preparedStatement2.setString(4, "Naisa");
//
//            int updteData = preparedStatement2.executeUpdate();
//            System.out.println("Baris di update: " + updteData);
//
//
//
//            // Delete data
//            String sql5 =  "DELETE FROM student where id = 8";
//            PreparedStatement preparedStatement3 = conn.prepareStatement(sql5);
//
//            int deleteData = preparedStatement3.executeUpdate();
//            System.out.println("Baris di hapus: " + deleteData);
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        // INI YG BARU

        ProductDAO productDAO = new ProductDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        try {

            // CRUD Product
            productDAO.insertProduct("Kereta", 12000, 2);
            productDAO.updateProduct(1, "Motor XSR");
            productDAO.deleteProduct(1);
            List<Product> productList = productDAO.getData();
            for (Product product : productList) {
                System.out.println(product);
            }


            // CRUD CUSTOMER
            customerDAO.insertCustomer("Naisa", "Jakarta", "2007-07-24", "active", 1234567);
            customerDAO.insertCustomer("Raisa", "Jakarta", "2007-07-24", "active", 1234567);
            customerDAO.updateCustomer(2, "Naisa ALifia Yuriza");
            customerDAO.deleteCustomer(3);
            List<Customer> customerList = customerDAO.getData();
            for (Customer customer : customerList){
                System.out.println(customer);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        // Ini Yang Transaction
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.purchasingProduct(3,1, 1);



    }

}