package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMain {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            statement = connection.createStatement();
            statement.executeQuery(
                    "select * from employees where name like '%t%'"
                    //""delete from employees where id=2"
                    //""update employees set salary=23999 where salary=24000"
                    //"drop table books"
                    //"alter table empoyees rename employees"
                    //"create table books(id int not null primary key, title varchar(100) not null, published date)"
                    //"insert into empoyees(name, address, salary) values ('Striełok', 'Jantar', 24000)"
            );
            /*
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
            resultSet.close();
             */
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
