package com.codecool.shop.dao.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoJDBCTest extends JDBCTest {

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
        assertEquals("description #1", category.getDescription());
        assertEquals("department #1", category.getDepartment());
    }

    @Test
    public void c_findReturnsNullWhenIdDoesNotExists() {
        ProductCategory category = dao.find(getUUIDFrom('3'));

        assertNull(category);
    }

    @Test
    public void d_getAllReturnsCorrectNumberOfRows() throws SQLException {
        ProductCategory category2 = new ProductCategory(getUUIDFrom('2'), "name #2", "department #2", "description #2");
        dao.add(category2);

        assertEquals(2, dao.getAll().size());
    }

    @Test
    public void d_getAllReturnsCorrectRows() {
        List<ProductCategory> categories = dao.getAll();

        ProductCategory category1 = categories.get(0);
        ProductCategory category2 = categories.get(1);
        assertEquals(getUUIDFrom('1'), category1.getId());
        assertEquals("name #1", category1.getName());
        assertEquals("department #1", category1.getDepartment());
        assertEquals("description #1", category1.getDescription());
        assertEquals(getUUIDFrom('2'), category2.getId());
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Postgres.cleanUpDb(dbName);
    }
}
