package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private final String id;
    private final User user;
    private final String name;
    private final String email;
    private final long phoneNumber;
    private final Address billingAddress;
    private final Address shippingAddress;
    private double totalPrice;
    private final List<OrderedProduct> products;
    private OrderStatus status;
    private String insertedAt;

    public Order(String id, OrderStatus status, String insertedAt) {
        this(id, null, status, null, null, 0, null, null);
        this.insertedAt = insertedAt;
    }

    public Order(User user, OrderStatus status, String name, String email, long phoneNumber, Address billingAddress, Address shippingAddress) {
        this(UUID.randomUUID().toString(), user, status, name, email, phoneNumber, billingAddress, shippingAddress);
    }

    public Order(String id, User user, OrderStatus status, String name, String email, long phoneNumber, Address billingAddress, Address shippingAddress) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void add(OrderedProduct product) {
        this.totalPrice += product.getProduct().getDefaultPrice();
        this.products.add(product);
    }

    public List<OrderedProduct> getProducts() {
        return products;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getDate() {
        return insertedAt;
    }
}
