package com.codecool.shop.config;

import com.codecool.shop.service.EnvironmentService;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;

public class DbConnection {

    private static EnvironmentService environments;

    static {
        environments = new EnvironmentService();
        try {
            environments.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DataSource getConnection() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        if (!environments.contains("APP_DB_USER_NAME")) {
            throw new RuntimeException("There is no APP_DB_USER_NAME in the environment file!");
        }

        if (!environments.contains("APP_DB_PASSWORD")) {
            throw new RuntimeException("There is no APP_DB_PASSWORD in the environment file!");
        }

        if (!environments.contains("APP_DB_NAME")) {
            throw new RuntimeException("There is no APP_DB_NAME in the environment file!");
        }

        dataSource.setDatabaseName(environments.get("APP_DB_NAME"));
        dataSource.setUser(environments.get("APP_DB_USER_NAME"));
        dataSource.setPassword(environments.get("APP_DB_PASSWORD"));
        return dataSource;
    }
}
