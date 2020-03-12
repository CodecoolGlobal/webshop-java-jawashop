package com.codecool.shop;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class JsonConverter {

    public String productToString(List<Product> products) {
        String category = products.get(0).getProductCategory().getName();
        String supplier = products.get(0).getSupplier().getName();
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Product product : products) {
            JsonObjectBuilder productBuilder = Json.createObjectBuilder();
            JsonObject productJson = productBuilder
                    .add("id", product.getId())
                    .add("name", product.getName())
                    .add("description", product.getDescription())
                    .add("price", product.getPrice())
                    .add("currency", product.getDefaultCurrency().getSymbol())
                    .add("supplier_name", product.getSupplier().getName())
                    .build();

            arrayBuilder.add(productJson);
        }
        JsonObject root = rootBuilder.add("category_name", category).add("supplier_name", supplier).add("products", arrayBuilder).build();
        return root.toString();
    }

    public String categoryToString(List<ProductCategory> productCategories) {
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (ProductCategory category : productCategories) {
            JsonObjectBuilder categoryBuilder = Json.createObjectBuilder();
            JsonObject productJson = categoryBuilder
                    .add("id", category.getId())
                    .add("name", category.getName())
                    .build();

            arrayBuilder.add(productJson);
        }
        JsonObject root = rootBuilder.add("categories", arrayBuilder).build();
        return root.toString();
    }

    public String supplierToString(List<Supplier> suppliers) {
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Supplier supplier : suppliers) {
            JsonObjectBuilder categoryBuilder = Json.createObjectBuilder();
            JsonObject productJson = categoryBuilder
                    .add("id", supplier.getId())
                    .add("name", supplier.getName())
                    .build();

            arrayBuilder.add(productJson);
        }
        JsonObject root = rootBuilder.add("suppliers", arrayBuilder).build();
        return root.toString();
    }
}
