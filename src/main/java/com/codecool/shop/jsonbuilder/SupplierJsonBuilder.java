package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.Supplier;

import javax.json.*;
import java.util.List;

public class SupplierJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddId;
    private boolean shouldAddName;
    private boolean shouldAddDescription;
    private boolean shouldAddProducts;
    private ProductJsonBuilder productBuilder;

    private SupplierJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static SupplierJsonBuilder create() {
        return new SupplierJsonBuilder();
    }

    public SupplierJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public SupplierJsonBuilder addName() {
        this.shouldAddName = true;
        return this;
    }

    public SupplierJsonBuilder addDescription() {
        this.shouldAddDescription = true;
        return this;
    }

    public SupplierJsonBuilder addProducts(ProductJsonBuilder productBuillder) {
        this.shouldAddProducts = true;
        this.productBuilder = productBuillder;
        return this;
    }

    public JsonArray runOn(List<Supplier> suppliers) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Supplier supplier : suppliers) {
            arrayBuilder.add(runOn(supplier));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(Supplier supplier) {
        if (this.shouldAddId) {
            this.rootObject.add("id", supplier.getId());
        }

        if (shouldAddName) {
            this.rootObject.add("name", supplier.getName());
        }

        if (shouldAddDescription) {
            this.rootObject.add("description", supplier.getDescription());
        }

        if (shouldAddProducts) {
            JsonArray productsArray = this.productBuilder.runOn(supplier.getProducts());
            this.rootObject.add("products", productsArray);
        }

        return this.rootObject.build();
    }
}
