package com.codecool.shop.dao.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
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
public class ProductDaoJDBCTest extends JDBCTest {

    private static ProductDao dao;
    private static ProductCategoryDao categoryDao;
    private static SupplierDao supplierDao;
    private static String dbName;

    @BeforeClass
    public static void beforeClass() throws SQLException, IOException {
        dbName = Postgres.getDbName();
        DataSource dataSource = Postgres.createTestDB(dbName);
        dao = new ProductDaoJDBC(dataSource);
        categoryDao = new ProductCategoryDaoJDBC(dataSource);
        supplierDao = new SupplierDaoJDBC(dataSource);
    }

    @Test
    public void a_addCanAddRows() throws SQLException {
        Product product1 = makeProduct('1', makeAndInsertCategory('4'), makeAndInsertSupplier('9'));
        Product product2 = makeProduct('2', makeAndInsertCategory('5'), makeAndInsertSupplier('8'));
        Product product3 = makeProduct('3', makeAndInsertCategory('6'), makeAndInsertSupplier('7'));

        dao.add(product1);
        dao.add(product2);
        dao.add(product3);

        List<Product> actual = dao.getAll();
        assertEquals(3, actual.size());
    }

    @Test
    public void b_findReturnsCorrectExistingRow() {
        Product product = dao.find(getUUIDFrom('1'));

        assertEquals(getUUIDFrom('1'), product.getId());
        assertEquals("Product #1", product.getName());
        assertEquals("Description #1", product.getDescription());
        assertEquals(1.0f, product.getDefaultPrice(), 3);
        assertEquals("HUF", product.getDefaultCurrency().getCurrencyCode());
        assertEquals(getUUIDFrom('4'), product.getProductCategory().getId());
        assertEquals(getUUIDFrom('9'), product.getSupplier().getId());
    }

    @Test
    public void c_findReturnsNullWhenIdDoesNotExists() {
        Product product = dao.find(getUUIDFrom('4'));

        assertNull(product);
    }

    @Test
    public void d_getAllReturnsCorrectNumberOfRows() {
        List<Product> products = dao.getAll();

        assertEquals(3, products.size());
    }

    @Test
    public void e_getBySupplierReturnsCorrectNumberOfRows() throws SQLException {
        Product product4 = makeProduct('4', makeCategory('6'), makeSupplier('7'));
        Product product5 = makeProduct('5', makeCategory('6'), makeSupplier('7'));
        dao.add(product4);
        dao.add(product5);

        List<Product> products = dao.getBy(makeSupplier('7'));

        assertEquals(3, products.size());
    }

    @Test
    public void e_getBySupplierReturnsCorrectRows() throws SQLException {
        List<Product> products = dao.getBy(makeSupplier('7'));

        Product product3 = products.get(0);
        Product product4 = products.get(1);
        Product product5 = products.get(2);
        assertEquals(getUUIDFrom('3'), product3.getId());
        assertEquals("Product #3", product3.getName());
        assertEquals("Description #3", product3.getDescription());
        assertEquals(1.0f, product3.getDefaultPrice(), 3);
        assertEquals("HUF", product3.getDefaultCurrency().getCurrencyCode());
        assertEquals(getUUIDFrom('6'), product3.getProductCategory().getId());
        assertEquals(getUUIDFrom('7'), product3.getSupplier().getId());

        assertEquals(getUUIDFrom('4'), product4.getId());
        assertEquals(getUUIDFrom('5'), product5.getId());
    }

    @Test
    public void e_getByProductCategoryReturnsCorrectNumberOfRows() throws SQLException {
        Product product6 = makeProduct('6', makeCategory('5'), makeAndInsertSupplier('2'));
        dao.add(product6);

        List<Product> products = dao.getBy(makeCategory('5'));

        assertEquals(2, products.size());
    }

    @Test
    public void e_getByProductCategoryReturnsCorrectRows() throws SQLException {
        List<Product> products = dao.getBy(makeCategory('5'));

        Product product2 = products.get(0);
        Product product6 = products.get(1);
        assertEquals(getUUIDFrom('2'), product2.getId());
        assertEquals("Product #2", product2.getName());
        assertEquals("Description #2", product2.getDescription());
        assertEquals(1.0f, product2.getDefaultPrice(), 3);
        assertEquals("HUF", product2.getDefaultCurrency().getCurrencyCode());
        assertEquals(getUUIDFrom('5'), product2.getProductCategory().getId());
        assertEquals(getUUIDFrom('8'), product2.getSupplier().getId());

        assertEquals(getUUIDFrom('6'), product6.getId());
    }

    @AfterClass
    public static void afterClass() throws SQLException {
        Postgres.cleanUpDb(dbName);
    }

    private Product makeProduct(char id, ProductCategory category, Supplier supplier) {
        return new Product(getUUIDFrom(id), String.format("Product #%s", id),1.0f, "HUF", String.format("Description #%s", id), category, supplier);
    }

    private ProductCategory makeCategory(char id) {
        return new ProductCategory(getUUIDFrom(id), String.format("Name #%s", id), String.format("Department #%s", id), String.format("Description #%s", id));
    }

    private ProductCategory makeAndInsertCategory(char id) throws SQLException {
        ProductCategory category = makeCategory(id);
        categoryDao.add(category);
        return category;
    }

    private Supplier makeSupplier(char id) {
        return new Supplier(getUUIDFrom(id), String.format("Name #%s", id), String.format("Description #%s", id));
    }

    private Supplier makeAndInsertSupplier(char id){
        Supplier supplier = makeSupplier(id);
        supplierDao.add(supplier);
        return supplier;
    }
}