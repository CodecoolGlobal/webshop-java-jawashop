package com.codecool.shop.dao;

import com.codecool.shop.model.Payment;

import java.sql.SQLException;

public interface PaymentDao {
    void add(Payment payment) throws SQLException;
}
