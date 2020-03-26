package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardDaoMem implements ShoppingCartDao {
    private List<Product> data = new ArrayList<>();
    private static ShoppingCardDaoMem instance = null;

    private ShoppingCardDaoMem(){}

    public static ShoppingCardDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCardDaoMem();
        }
        return instance;
    }

    public static ShoppingCartDao create(){
        return new ShoppingCardDaoMem();
    }

    @Override
    public void add(Product product) {
        this.data.add(product);
    }

    @Override
    public Product find(String id) {
        return data.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void remove(String id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return data;
    }
}
