package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMain {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
            statement = connection.createStatement();

            rs = statement.executeQuery("select * from empoyees");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                int salary = rs.getInt("salary");

                System.out.println(id+"\t"+name+"\t"+address+"\t"+salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        /*
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");

            if(connection == null) {
                System.out.println("Cannot connect to DB");
            } else {
                System.out.println("Connection exists");
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}
