package com.codecool.shop.dao;

import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.User;

import java.util.List;

public interface ShoppingCartDao {
    void add(CartItem item);
    CartItem find(Product product);
    void remove(CartItem cartItem);
    void update(CartItem cartItem);
    List<CartItem> getAll(User user);
}
