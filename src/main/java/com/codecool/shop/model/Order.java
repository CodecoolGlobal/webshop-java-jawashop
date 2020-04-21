package com.codecool.shop.model;

import java.util.UUID;

public class Order {

    private final String id;
    private final String name;
    private final String email;
    private final long phoneNumber;
    private final Address billingAddress;
    private final Address shippingAddress;
    private double totalPrice;

    public Order(String name, String email, long phoneNumber, Address billingAddress, Address shippingAddress) {
        this(UUID.randomUUID().toString(), name, email, phoneNumber, billingAddress, shippingAddress);
    }

    public Order(String id, String name, String email, long phoneNumber, Address billingAddress, Address shippingAddress) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getId() {
        return id;
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

    public void increaseTotalPrice(double value) {
        this.totalPrice += value;
    }
}
