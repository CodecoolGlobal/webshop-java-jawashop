package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import java.util.List;

public interface ShoppingCartDao {
    void add(Product product);
    Product find(String id);
    void remove(String id);

    List<Product> getAll();
}
