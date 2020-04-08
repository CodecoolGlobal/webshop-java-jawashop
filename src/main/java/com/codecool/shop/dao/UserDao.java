package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.SQLException;

public interface UserDao {
    void add(User user) throws SQLException;
    boolean isExists(String email) throws SQLException;
}
