package com.codecool.shop.dao.JDBC;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SupplierDaoJDBCTest extends JDBCTest {

    private static SupplierDaoJDBC dao;
    private static String dbName;

    @BeforeClass
    public static void beforeClass() throws SQLException, IOException {        dbName = Postgres.getDbName();
        dbName = Postgres.getDbName();
        DataSource dataSource = Postgres.createTestDB(dbName);
        dao = new SupplierDaoJDBC(dataSource);
    }

    @Test
    public void a_addCanAddRows() throws SQLException {
        Supplier supplier = new Supplier(getUUIDFrom('1'), "Name #1", "Description #1");

        dao.add(supplier);

        assertEquals(1, dao.getAll().size());
    }

    @Test
    public void b_findReturnsCorrectExistingRow() {
        Supplier supplier = dao.find(getUUIDFrom('1'));

        assertEquals(getUUIDFrom('1'), supplier.getId());
        assertEquals("Name #1", supplier.getName());
        assertEquals("Description #1", supplier.getDescription());
    }

    @Test
    public void c_findReturnsNullWhenIdDoesNotExists() {
        Supplier supplier = dao.find(getUUIDFrom('3'));

        assertNull(supplier);
    }

    @Test
    public void d_getAllReturnsCorrectNumberOfRows() {
        Supplier supplier2 = new Supplier(getUUIDFrom('2'), "Name #2", "Description #2");
        dao.add(supplier2);

        assertEquals(2, dao.getAll().size());
    }

    @Test
    public void d_getAllReturnsCorrectRows() {
        List<Supplier> suppliers = dao.getAll();

        Supplier supplier1 = suppliers.get(0);
        Supplier supplier2 = suppliers.get(1);
        assertEquals(getUUIDFrom('1'), supplier1.getId());
        assertEquals("Name #1", supplier1.getName());
        assertEquals("Description #1", supplier1.getDescription());
        assertEquals(getUUIDFrom('2'), supplier2.getId());
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Postgres.cleanUpDb(dbName);
    }
}
