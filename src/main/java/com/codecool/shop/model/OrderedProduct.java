package com.codecool.shop.model;

import java.util.UUID;

public class OrderedProduct {

    private final String id;
    private final Order order;
    private final Product product;
    private final int quantity;

    public OrderedProduct(Order order, Product product, int quantity) {
        this(UUID.randomUUID().toString(), order, product, quantity);
    }

    public OrderedProduct(String id, Order order, Product product, int quantity) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
