package com.codecool.shop.model;

import java.util.UUID;

public class Address {

    private final String id;
    private final String country;
    private final String city;
    private final String zipCode;
    private final String address;

    public Address(String country, String city, String zipCode, String address) {
        this(UUID.randomUUID().toString(), country, city, zipCode, address);
    }

    public Address(String id, String country, String city, String zipCode, String address) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }
}
