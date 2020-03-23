package com.codecool.shop.config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DbConnection {
//    DataSource dataSource;

//    public DbConnection() throws SQLException {
//        this.dataSource = connect();
//    }

//    private DataSource connect() throws SQLException {
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("coolshop_db");
//        dataSource.setUser("postgres");
//        dataSource.setPassword("postgres");
//        System.out.println("Trying to connect...");
//        dataSource.getConnection().close();
//        System.out.println("Connection OK");
//        return dataSource;
//    }

    public static DataSource getConnection() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("coolshop_db");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");
        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");
        return dataSource;

    }

}
