package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCardDaoMemTest {
    ShoppingCartDao shoppingCartDao;

    @BeforeEach
    public void init(){
        shoppingCartDao = ShoppingCardDaoMem.getInstance();
    }

    @Test
    public void addTest(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(product);
        shoppingCartDao.add(makeProduct());

        assertSame(product, shoppingCartDao.find(product.getId()));
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