package com.codecool.shop.model;

import java.util.UUID;

public class OrderStatus {

    private final UUID id;
    private final String name;

    public OrderStatus(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
    public static OrderStatus asChecked() {
        return new OrderStatus(null, "Checked");
    }

    public static OrderStatus asPaid() {
        return new OrderStatus(null, "Paid");
    }

    public static OrderStatus asConfirmed() {
        return new OrderStatus(null, "Confirmed");
    }

    public static OrderStatus asShipped() {
        return new OrderStatus(null, "Shipped");
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
