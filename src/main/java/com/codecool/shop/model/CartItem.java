package com.codecool.shop.model;

import java.util.UUID;

public class CartItem {

    private final String id;
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this(UUID.randomUUID().toString(), product, quantity);
    }

    public CartItem(String id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void changeQuantity(int volume) {
        this.quantity += volume;
    }
}
