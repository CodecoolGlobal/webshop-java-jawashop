package com.codecool.shop.dao;

import com.codecool.shop.model.Address;
import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao {
    void add(Address address) throws SQLException;
    List<Address> getAllBy(User user) throws SQLException;
}
