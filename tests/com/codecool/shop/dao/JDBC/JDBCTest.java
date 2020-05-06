package com.codecool.shop.dao.JDBC;

import java.util.Arrays;

public abstract class JDBCTest {

    protected String getUUIDFrom(char id) {
        char[] uuid = new char[36];
        Arrays.fill(uuid, id);
        uuid[8] = '-';
        uuid[13] = '-';
        uuid[18] = '-';
        uuid[23] = '-';
        return new String(uuid);
    }
}
