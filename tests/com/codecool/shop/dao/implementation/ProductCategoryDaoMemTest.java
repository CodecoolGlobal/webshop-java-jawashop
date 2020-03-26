package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void findReturnProductCategoryIfExists(){
        ProductCategory productCategory = makeCategory();

        productCategoryDao.add(makeCategory());
        productCategoryDao.add(productCategory);
        productCategoryDao.add(makeCategory());

        assertSame(productCategory, productCategoryDao.find(productCategory.getId()));
    }

    @Test
    public void findReturnNullIfProductCategoryNotExists(){
        ProductCategory productCategory = makeCategory();

        productCategoryDao.add(makeCategory());
        productCategoryDao.add(makeCategory());
        productCategoryDao.add(makeCategory());

        assertNull(productCategoryDao.find(productCategory.getId()));
    }


    @Test
    public void removeProductCategoryReturnNull(){
        ProductCategory productCategory = makeCategory();

        productCategoryDao.add(makeCategory());
        productCategoryDao.add(productCategory);
        productCategoryDao.add(makeCategory());

        productCategoryDao.remove(productCategory.getId());
        assertNull(productCategoryDao.find(productCategory.getId()));

    }

    @Test
    public void getReturnsAllProductCategoriesIfProductListNotNull(){
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