package com.codecool.shop.dao.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoJDBCTest {

    private static ProductCategoryDao dao;
    private static final String dbName;

    static {
        dbName = Postgres.getDbName();
    }

    @BeforeClass
    public static void beforeClass() throws SQLException, IOException {
        DataSource dataSource = Postgres.createTestDB(dbName);
        dao = new ProductCategoryDaoJDBC(dataSource);
    }

    @Test
    public void a_addCanAddRows() throws SQLException {
        ProductCategory productCategory = new ProductCategory(getUUIDFrom('1'), "name #1", "department #1", "description #1");
        dao.add(productCategory);
        List<ProductCategory> actual = dao.getAll();

        assertEquals(1, actual.size());
    }

    @Test
    public void b_findReturnsCorrectExistingRow() {
        ProductCategory category = dao.find(getUUIDFrom('1'));

        assertEquals(getUUIDFrom('1'), category.getId());
        assertEquals("name #1", category.getName());
        assertEquals("department #1", category.getDepartment());
        assertEquals("description #1", category.getDescription());
    }

    @Test
    public void c_findReturnsNullWhenIdDoesNotExists() {
        ProductCategory category = dao.find(getUUIDFrom('3'));

        assertNull(category);
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Postgres.cleanUpDb(dbName);
    }

    private String getUUIDFrom(char id) {
        char[] uuid = new char[36];
        Arrays.fill(uuid, id);
        uuid[8] = '-';
        uuid[13] = '-';
        uuid[18] = '-';
        uuid[23] = '-';
        return new String(uuid);
    }
}
