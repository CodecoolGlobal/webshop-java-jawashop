package com.codecool.shop.model;

import java.util.UUID;

public class CreditCard {

    private final String id;
    private final long number;
    private final String ownerName;
    private final String expireDate;
    private final int code;

    public CreditCard(long number, String ownerName, String expireDate, int code) {
        this(UUID.randomUUID().toString(), number, ownerName, expireDate, code);
    }

    public CreditCard(String id, long number, String ownerName, String expireDate, int code) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.expireDate = expireDate;
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public long getNumber() {
        return number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public int getCode() {
        return code;
    }
}
