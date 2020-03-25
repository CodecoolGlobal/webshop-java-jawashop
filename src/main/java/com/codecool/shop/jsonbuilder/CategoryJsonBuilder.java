package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.ProductCategory;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class CategoryJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddId;
    private boolean shouldAddName;
    private boolean shouldAddDescription;
    private boolean shouldAddDepartment;
    private boolean shouldAddProducts;
    private ProductJsonBuilder productBuilder;

    private CategoryJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static CategoryJsonBuilder create() {
        return new CategoryJsonBuilder();
    }

    public CategoryJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public CategoryJsonBuilder addName() {
        this.shouldAddName = true;
        return this;
    }

    public CategoryJsonBuilder addDescription() {
        this.shouldAddDescription = true;
        return this;
    }

    public CategoryJsonBuilder addDepartment() {
        this.shouldAddDepartment = true;
        return this;
    }

    public CategoryJsonBuilder addProducts(ProductJsonBuilder productBuilder) {
        this.shouldAddProducts = true;
        this.productBuilder = productBuilder;
        return this;
    }

    public JsonArray runOn(List<ProductCategory> categories) {
        javax.json.JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (ProductCategory category : categories) {
            arrayBuilder.add(runOn(category));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(ProductCategory category) {
        if (this.shouldAddId) {
            this.rootObject.add("id", category.getId());
        }

        if (shouldAddName) {
            this.rootObject.add("name", category.getName());
        }

        if (shouldAddDescription) {
            this.rootObject.add("description", category.getDescription());
        }

        if (shouldAddDepartment) {
            this.rootObject.add("price", category.getDepartment());
        }

        if (shouldAddProducts) {
            this.rootObject.add("products", productBuilder.runOn(category.getProducts()));
        }

        return this.rootObject.build();
    }
}
