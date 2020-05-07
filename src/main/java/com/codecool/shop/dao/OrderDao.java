package com.codecool.shop.dao;

import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void add(Order order);
    boolean isExists(String id) throws InternalServerException;
    void updateStatus(Order order) throws InternalServerException;
    List<Order> getAllWithProducts(User user) throws SQLException;
}
