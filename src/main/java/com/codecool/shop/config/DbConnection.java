package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DbConnection {

    public static DataSource getConnection() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("coolshop_db");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        return dataSource;

    }

}
