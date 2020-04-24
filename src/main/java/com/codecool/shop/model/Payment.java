package com.codecool.shop.model;

import java.util.UUID;

public class Payment {

    private final String id;
    private final Order order;
    private final CreditCard creditCard;

    public Payment(Order order, CreditCard creditCard) {
        this(UUID.randomUUID().toString(), order, creditCard);
    }

    public Payment(String id, Order order, CreditCard creditCard) {
        this.id = id;
        this.order = order;
        this.creditCard = creditCard;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
