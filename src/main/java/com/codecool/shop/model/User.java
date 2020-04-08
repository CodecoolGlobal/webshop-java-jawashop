package com.codecool.shop.model;

import java.util.UUID;

public class User {

    private final String id;
    private final String name;
    private final String email;
    private String password;

    private String authToken;

    public User(String id, String authToken) {
        this(id, null, null, null, authToken);
    }

    public User(String name, String email, String password) {
        this(UUID.randomUUID().toString(), name, email, password, null);
    }

    public User(String id, String name, String email, String password, String authToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authToken = authToken;
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

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
