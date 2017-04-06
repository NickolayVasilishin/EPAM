package com.epam.jmp.jdbc.dao;

import com.epam.jmp.jdbc.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDAO implements UserDAO {
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findUsersWithLotsOfFriends(int numberOfFriends) throws SQLException {
        /*
            Get all friendships (in both directions, except friendships that differ only in direction),
             union them to get one column
             count
             select only those ids, who have more than numberOfFriends
             join with users table
         */
        String sql = "SELECT *, COUNT(*) as c FROM users WHERE " +
                "##(SELECT userid1 FROM friendships " +
                "   UNION " +
                "   (SELECT userid2 FROM friendships EXCEPT userid1)" +
                "AS userid" +
                ") WHERE c > ?" +
                "DISTINCT";

        return findUsersBySql(numberOfFriends, sql);
    }

    public List<User> findUsersWithLotsOfLikes(int numberOfLikes) throws SQLException {
        /*
            Count post in likes table,
            select those who have more than numberOfLikes,
            join with posts table to get userids
            join with users table to get users
         */
        String sql = "";
        return findUsersBySql(numberOfLikes, sql);
    }

    private List<User> findUsersBySql(int numericCriteria, String sql) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numericCriteria);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getDate("birthdate")
                ));
            }
            rs.close();
            ps.close();
        }
        return users;
    }
}
