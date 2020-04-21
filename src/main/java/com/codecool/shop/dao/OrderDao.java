package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.sql.SQLException;

public interface OrderDao {
    void add(Order order);
    boolean isExists(String id) throws SQLException;
}
