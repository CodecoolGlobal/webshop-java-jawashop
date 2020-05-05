package com.codecool.shop.model;

import java.util.UUID;

public class CartItem {

    private final String id;
    private final User owner;
    private final Product product;
    private int quantity;

    public CartItem(User owner, Product product, int quantity) {
        this(UUID.randomUUID().toString(), owner, product, quantity);
    }

    public CartItem(String id, User owner, Product product, int quantity) {
        this.id = id;
        this.owner = owner;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
