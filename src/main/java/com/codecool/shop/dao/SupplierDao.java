package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {
    void add(Supplier supplier);
    Supplier find(String id);
    List<Supplier> getAll();
}
