package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.service.EnvironmentService;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Postgres {

    private static final String username;
    private static final String password;
    private static final EnvironmentService environments;

    static {
        environments = new EnvironmentService();
        try {
            environments.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        username = environments.get("APP_DB_USER_NAME");
        password = environments.get("APP_DB_PASSWORD");
    }

    protected static String getDbName() {
        return environments.get("APP_DB_NAME")
                .concat("_test_")
                .concat(UUID.randomUUID().toString())
                .replace("-", "_");
    }

    protected static DataSource createTestDB(String dbName) throws SQLException, IOException {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username, password);
        Statement statement = c.createStatement();
        statement.execute(String.format("CREATE DATABASE %s;", dbName));
        DataSource dataSource = DbConnection.getConnection(dbName);
        createSchemaFor(dataSource);
        return dataSource;
    }

    protected static void cleanUpDb(String dbName) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username, password);
        Statement statement = c.createStatement();
        statement.executeUpdate(String.format("DROP DATABASE %s;", dbName));
    }

    private static void createSchemaFor(DataSource dataSource) throws IOException, SQLException {
        URL fileURL = Postgres.class.getClassLoader().getResource("schema.sql");
        if (fileURL == null) {
            throw new FileNotFoundException("No DB migration file found!");
        }

        String fileContent = Files.readString(Paths.get(new File(fileURL.getFile()).getAbsolutePath()), StandardCharsets.UTF_8);

        try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            statement.execute(fileContent);
        }
    }
}
