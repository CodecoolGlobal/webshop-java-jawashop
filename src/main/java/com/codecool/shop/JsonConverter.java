package com.codecool.shop;

import com.codecool.shop.model.Product;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class JsonConverter {

    public String toString(List<Product> products) {
        String category = products.get(1).getProductCategory().getName();
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
        JsonObject root = rootBuilder.add("category_name", category).add("products", arrayBuilder).build();
        return root.toString();
    }
}