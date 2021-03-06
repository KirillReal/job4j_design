package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        ConnectionRead connectionRead
                = new ConnectionRead("./src/main/java/ru.job4j/resources/app.properties");
        connectionRead.load();
        String url = connectionRead.get(ConnectionRead.URL);
        String login = connectionRead.get(ConnectionRead.LOGIN);
        String password = connectionRead.get(ConnectionRead.PASSWORD);
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
