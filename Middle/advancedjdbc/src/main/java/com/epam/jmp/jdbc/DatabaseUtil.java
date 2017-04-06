package com.epam.jmp.jdbc;

import com.epam.jmp.jdbc.dao.JdbcUserDAO;
import com.epam.jmp.jdbc.dao.UserDAO;
import com.epam.jmp.jdbc.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseUtil {
    DataSource dataSource;
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private final String CREATE_USERS_TABLE = "CREATE TABLE users (\n" +
            "id INT NOT NULL PRIMARY KEY,\n" +
            "name VARCHAR NOT NULL,\n" +
            "surname VARCHAR NOT NULL,\n" +
            "birthdate DATE NOT NULL,\n" +
            "  \n" +
            ") AS SELECT * FROM CSVREAD('advancedjdbc/src/main/resources/data/users.csv');";

    private final String CREATE_POSTS_TABLE = "CREATE TABLE posts (\n" +
            "id INT NOT NULL PRIMARY KEY,\n" +
            "userid INT NOT NULL,\n" +
            "text VARCHAR NOT NULL,\n" +
            "timestamp TIMESTAMP NOT NULL,\n" +
            "  \n" +
            ") AS SELECT * FROM CSVREAD('advancedjdbc/src/main/resources/data/posts.csv');";

    private final String CREATE_FRIENDSHIPS_TABLE = "CREATE TABLE friendships (\n" +
            "userid1 INT NOT NULL,\n" +
            "userid2  INT NOT NULL,\n" +
            "timestamp TIMESTAMP NOT NULL,\n" +
            "  \n" +
            ") AS SELECT * FROM CSVREAD('advancedjdbc/src/main/resources/data/friendships.csv');";

    private final String CREATE_LIKES_TABLE = "CREATE TABLE likes (\n" +
            "userid INT NOT NULL,\n" +
            "postid  INT NOT NULL,\n" +
            "timestamp TIMESTAMP NOT NULL,\n" +
            "  \n" +
            ") AS SELECT * FROM CSVREAD('advancedjdbc/src/main/resources/data/likes.csv');";

    public void createTablesWithData() {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement(CREATE_USERS_TABLE).execute();
            connection.prepareStatement(CREATE_POSTS_TABLE).execute();
            connection.prepareStatement(CREATE_FRIENDSHIPS_TABLE).execute();
            connection.prepareStatement(CREATE_LIKES_TABLE).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        DatabaseUtil databaseUtil = (DatabaseUtil) context.getBean("databaseUtil");
        databaseUtil.createTablesWithData();

        UserDAO jdbcUserDAO = (UserDAO) context.getBean("jdbcUserDAO");
        try {
            List<User> usersWithLotsOfFriends = jdbcUserDAO.findUsersWithLotsOfFriends(100);
            List<User> usersWithLotsOfLikes = jdbcUserDAO.findUsersWithLotsOfLikes(100);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
