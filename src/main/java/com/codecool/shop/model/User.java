package com.codecool.shop.model;

import java.util.UUID;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private String password;

    public User(String name, String email, String password) {
        this(UUID.randomUUID().toString(), name, email, password);
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        String tmp = password;
        password = null;
        return tmp;
    }
}
