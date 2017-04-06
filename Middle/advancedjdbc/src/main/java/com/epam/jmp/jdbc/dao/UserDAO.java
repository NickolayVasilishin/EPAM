package com.epam.jmp.jdbc.dao;

import com.epam.jmp.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> findUsersWithLotsOfFriends(int numberOfFriends) throws SQLException;

    List<User> findUsersWithLotsOfLikes(int numberOfLikes) throws SQLException;
}