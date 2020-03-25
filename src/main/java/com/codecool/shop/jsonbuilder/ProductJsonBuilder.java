package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.Product;

import javax.json.*;
import java.util.List;

public class ProductJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddId;
    private boolean shouldAddName;
    private boolean shouldAddDescription;
    private boolean shouldAddPrice;
    private boolean shouldAddCurrency;
    private CurrencyJsonBuilder currencyBuilder;
    private boolean shouldAddCategory;
    private CategoryJsonBuilder categoryBuilder;
    private boolean shouldAddSupplier;
    private SupplierJsonBuilder supplierBuilder;

    private ProductJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static ProductJsonBuilder create() {
        return new ProductJsonBuilder();
    }

    public ProductJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public ProductJsonBuilder addName() {
        this.shouldAddName = true;
        return this;
    }

    public ProductJsonBuilder addDescription() {
        this.shouldAddDescription = true;
        return this;
    }

    public ProductJsonBuilder addPrice() {
        this.shouldAddPrice = true;
        return this;
    }

    public ProductJsonBuilder addCurrency(CurrencyJsonBuilder currencyBuilder) {
        this.shouldAddCurrency = true;
        this.currencyBuilder = currencyBuilder;
        return this;
    }

    public ProductJsonBuilder addCategory(CategoryJsonBuilder categoryBuilder) {
        this.shouldAddCategory = true;
        this.categoryBuilder = categoryBuilder;
        return this;
    }

    public ProductJsonBuilder addSupplier(SupplierJsonBuilder supplierBuilder) {
        this.shouldAddSupplier = true;
        this.supplierBuilder = supplierBuilder;
        return this;
    }

    public JsonArray runOn(List<Product> products) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Product supplier : products) {
            arrayBuilder.add(runOn(supplier));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(Product product) {
        if (this.shouldAddId) {
            this.rootObject.add("id", product.getId());
        }

        if (shouldAddName) {
            this.rootObject.add("name", product.getName());
        }

        if (shouldAddDescription) {
            this.rootObject.add("description", product.getDescription());
        }

        if (shouldAddPrice) {
            this.rootObject.add("price", product.getDefaultPrice());
        }

        if (shouldAddCurrency) {
            JsonObject currencyObject = this.currencyBuilder.runOn(product.getDefaultCurrency());
            this.rootObject.add("currency", currencyObject);
        }

        if (shouldAddCategory) {
            JsonObject categoryObject = this.categoryBuilder.runOn(product.getProductCategory());
            this.rootObject.add("category", categoryObject);
        }

        if (shouldAddSupplier) {
            JsonObject supplierObject = this.supplierBuilder.runOn(product.getSupplier());
            this.rootObject.add("supplier", supplierObject);
        }

        return this.rootObject.build();
    }
}
