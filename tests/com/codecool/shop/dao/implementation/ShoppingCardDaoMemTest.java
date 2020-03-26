package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCardDaoMemTest {
    ShoppingCartDao shoppingCartDao;

    @BeforeEach
    public void init(){
        shoppingCartDao = ShoppingCardDaoMem.getInstance();
    }

    @Test
    public void findReturnProductIfExists(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(product);
        shoppingCartDao.add(makeProduct());

        assertSame(product, shoppingCartDao.find(product.getId()));
    }

    @Test
    public void findReturnNullIfProductNotExists(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(makeProduct());

        assertSame(null, shoppingCartDao.find(product.getId()));
    }

    @Test
    public void removeProductReturnNull(){
        Product product = makeProduct();

        shoppingCartDao.add(makeProduct());
        shoppingCartDao.add(product);
        shoppingCartDao.add(makeProduct());

        shoppingCartDao.remove(product.getId());
        assertSame(null, shoppingCartDao.find(product.getId()));

    }

    @Test
    public void getReturnsAllProductsIfProductListNotNull(){
        List<Product> products = new ArrayList<>();

        products.add(makeProduct());
        products.add(makeProduct());
        products.add(makeProduct());
        for (Product product: products) {
            shoppingCartDao.add(product);
        }

        assertEquals(products, shoppingCartDao.getAll());


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