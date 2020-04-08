package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface UserDao {
    void add(User user) throws SQLException;
    boolean isExists(String email) throws SQLException;
    boolean isAuthTokenExists(String value) throws SQLException;
    Optional<User> find(String email, String password) throws SQLException;
    void update(User user) throws SQLException;
}
