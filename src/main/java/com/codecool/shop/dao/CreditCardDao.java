package com.codecool.shop.dao;

import com.codecool.shop.model.CreditCard;

import java.sql.SQLException;

public interface CreditCardDao {
    void add(CreditCard creditCard) throws SQLException;
}
