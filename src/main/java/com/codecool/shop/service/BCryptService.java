package com.codecool.shop.service;

import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class BCryptService implements CryptoService {

    private final String salt;

    public BCryptService() throws IOException {
        URL fileUri = getClass().getClassLoader().getResource(".env");

        if (fileUri == null) {
            throw new FileNotFoundException("No environment file configured!");
        }

        String path = fileUri.getFile();

        File environmentFile = new File(fileUri.getFile());

        Map<String, String> envVariables = parse(environmentFile);

        if (!envVariables.containsKey("APP_SALT")) {
            throw new RuntimeException("There is no APP_SALT in the environment file!");
        }

        salt = envVariables.get("APP_SALT");
    }

    @Override
    public String hash(String value) {
        return BCrypt.hashpw(value, salt);
    }

    private static Map<String, String> parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
        Map<String, String> values = new HashMap<String, String>();

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("=");

            if (parts.length != 2) {
                throw new InvalidPropertiesFormatException(String.format("Line (%s) is not a valid key-value pair!", lines.get(i)));
            }

            values.put(parts[0], parts[1]);
        }

        return values;
    }
}
