package com.codecool.shop.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;

public class EnvironmentService {

    private final URL fileUri;
    private final Map<String, String> variables;

    public EnvironmentService() {
        this.fileUri = getClass().getClassLoader().getResource(".env");
        this.variables = new HashMap<>();
    }

    public void initialize() throws IOException {
        if (this.fileUri == null) {
            throw new FileNotFoundException("No environment file configured!");
        }

        String path = fileUri.getFile();

        File environmentFile = new File(fileUri.getFile());

        parse(environmentFile);
    }

    public boolean contains(String key) {
        return this.variables.containsKey(key);
    }

    public String get(String key) {
        return this.variables.get(key);
    }

    private void parse(File file) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split("=");

            if (parts.length != 2) {
                throw new InvalidPropertiesFormatException(String.format("Line (%s) is not a valid key-value pair!", lines.get(i)));
            }

            this.variables.put(parts[0], parts[1]);
        }
    }
}
