package com.codecool.shop.model;

import java.util.List;

public class CategoryWithProducts extends Stringifier {
    public ProductCategory productCategory;
    public List<Product> products;

    public CategoryWithProducts(ProductCategory productCategory, List<Product> products) {
        this.productCategory = productCategory;
        this.products = products;
    }
}
