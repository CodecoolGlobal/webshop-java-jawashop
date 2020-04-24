package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderedProduct;

import java.sql.SQLException;
import java.util.List;

public interface OrderedProductDao {

    void add(OrderedProduct orderedProduct);
    List<OrderedProduct> findBy(Order order) throws SQLException;
}
