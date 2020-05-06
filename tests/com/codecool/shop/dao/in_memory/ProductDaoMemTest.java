package com.codecool.shop.dao.in_memory;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {
    ProductDao productDao;

    @BeforeEach
    public void init(){
        productDao = ProductDaoMem.create();
    }

    @Test
    public void findReturnProductIfExists() throws SQLException {
        Product product = makeProduct();

        productDao.add(makeProduct());
        productDao.add(product);
        productDao.add(makeProduct());

        assertSame(product, productDao.find(product.getId()));
    }

    @Test
    public void findReturnNullIfProductNotExists() throws SQLException {
        Product product = makeProduct();

        productDao.add(makeProduct());
        productDao.add(makeProduct());
        productDao.add(makeProduct());

        assertNull(productDao.find(product.getId()));
    }

    @Test
    public void getReturnsAllProductsIfProductListNotNull() throws SQLException {
        List<Product> products = new ArrayList<>();

        products.add(makeProduct());
        products.add(makeProduct());
        products.add(makeProduct());
        for (Product product: products) {
            productDao.add(product);
        }

        assertEquals(products, productDao.getAll());
    }

    @Test
    public void getByReturnsAllProductsFromOneSupplierIfProductListNotNull() throws SQLException {
        Supplier supplier = makeSupplier();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = makeProduct();
            product.setSupplier(supplier);
            products.add(product);
        }

        for (Product product: products) {
            productDao.add(product);
        }

        assertEquals(products, productDao.getBy(supplier));
    }

    @Test
    public void getByReturnsAllProductsFromOneCategoryIfProductListNotNull() throws SQLException {
        ProductCategory productCategory = makeCategory();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = makeProduct();
            product.setProductCategory(productCategory);
            products.add(product);
        }

        for (Product product: products) {
            productDao.add(product);
        }

        assertEquals(products, productDao.getBy(productCategory));
    }

    private Product makeProduct(){
        return new Product(UUID.randomUUID().toString(), "name",12.12f, "USD", "desc",makeCategory(),makeSupplier() );
    }

    private ProductCategory makeCategory(){
        return new ProductCategory(null, null, null, null);
    }

    private Supplier makeSupplier(){
        return new Supplier(null, null, null);
    }
}