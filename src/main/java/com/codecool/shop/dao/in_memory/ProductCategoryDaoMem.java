package com.codecool.shop.dao.in_memory;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> data = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        data.add(category);
    }

    @Override
    public ProductCategory find(String id) {
        return data.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> getProductsByCategoryId(String id, List<Product> products) {
        List<Product> productsByCategory = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().getId().equals(id)) {
                productsByCategory.add(product);
            }
        }
        return productsByCategory;
    }

    @Override
    public void remove(String id) {
        data.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return data;
    }
}
