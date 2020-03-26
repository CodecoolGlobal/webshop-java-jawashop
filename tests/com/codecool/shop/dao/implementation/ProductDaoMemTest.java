package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoMemTest {
    ProductDao productDao;
    @BeforeEach
    public void init(){
        productDao = ProductDaoMem.getInstance();
    }


    @Test
    public void addTest(){
        Product product = makeProduct();

        productDao.add(makeProduct());
        productDao.add(product);
        productDao.add(makeProduct());

        assertSame(product, productDao.find(product.getId()));
    }

    @Test
    public void removeTest({

    })

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