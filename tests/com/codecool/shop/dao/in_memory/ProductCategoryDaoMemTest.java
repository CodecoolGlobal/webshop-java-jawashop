package com.codecool.shop.dao.in_memory;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest {
    ProductCategoryDao productCategoryDao;

    @BeforeEach
    public void init(){
        productCategoryDao = ProductCategoryDaoMem.getInstance();
    }

    @Test
    public void findReturnProductCategoryIfExists() throws SQLException {
        ProductCategory productCategory = makeCategory();

        productCategoryDao.add(makeCategory());
        productCategoryDao.add(productCategory);
        productCategoryDao.add(makeCategory());

        assertSame(productCategory, productCategoryDao.find(productCategory.getId()));
    }

    @Test
    public void findReturnNullIfProductCategoryNotExists() throws SQLException {
        ProductCategory productCategory = makeCategory();

        productCategoryDao.add(makeCategory());
        productCategoryDao.add(makeCategory());
        productCategoryDao.add(makeCategory());

        assertNull(productCategoryDao.find(productCategory.getId()));
    }

    @Test
    public void getReturnsAllProductCategoriesIfProductListNotNull() throws SQLException {
        List<ProductCategory> productCategories = new ArrayList<>();

        productCategories.add(makeCategory());
        productCategories.add(makeCategory());
        productCategories.add(makeCategory());
        for (ProductCategory productCategory: productCategories) {
            productCategoryDao.add(productCategory);
        }

        assertEquals(productCategories, productCategoryDao.getAll());
    }

    private ProductCategory makeCategory(){
        return new ProductCategory(UUID.randomUUID().toString(), null, null, null);
    }
}
