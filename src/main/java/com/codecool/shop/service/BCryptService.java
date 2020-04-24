package com.codecool.shop.service;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class BCryptService implements CryptoService {

    private final String salt;

    public BCryptService() throws IOException {
        this(new EnvironmentService());
    }

    public BCryptService(EnvironmentService environmentService) throws IOException {
        environmentService.initialize();

        if (!environmentService.contains("APP_SALT")) {
            throw new RuntimeException("There is no APP_SALT in the environment file!");
        }

        salt = environmentService.get("APP_SALT");
    }

    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, salt);
    }
}
