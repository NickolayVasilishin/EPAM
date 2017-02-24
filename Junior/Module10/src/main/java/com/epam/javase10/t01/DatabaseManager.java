package com.epam.javase10.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Nick on 09.05.2016.
 */
public class DatabaseManager implements AutoCloseable {
    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/test");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try (Statement statement = connection.createStatement()){
            statement.executeQuery("CREATE TABLE library (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT," +
                    "book VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255) NOT NULL, " +
                    "count INT NOT NULL" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBooks() {
        try (Statement statement = connection.createStatement()){
            statement.executeQuery("INSERT INTO library (book, author, count) VALUES (\"Fahrenheit 451\", \"Raymond Bradbury\", 100)");
            statement.executeQuery("INSERT INTO library (book, author, count) VALUES (\"The End of Eternity\", \"Isaac Asimov\", 100)");
            statement.executeQuery("INSERT INTO library (book, author, count) VALUES (\"Foundation\", \"Isaac Asimov\", 50)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try (Statement statement = connection.createStatement()){
            statement.executeQuery("UPDATE library SET count=50 WHERE book = \"Foundation\";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }
}
