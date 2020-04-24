package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.OrderedProduct;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class OrderedProductJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddProduct;
    private ProductJsonBuilder productBuilder;
    private boolean shouldAddQuantity;

    private OrderedProductJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static OrderedProductJsonBuilder create() {
        return new OrderedProductJsonBuilder();
    }

    public OrderedProductJsonBuilder addProduct(ProductJsonBuilder productBuilder) {
        this.shouldAddProduct = true;
        this.productBuilder = productBuilder;
        return this;
    }

    public OrderedProductJsonBuilder addQuantity() {
        this.shouldAddQuantity = true;
        return this;
    }

    public JsonArray runOn(List<OrderedProduct> products) {
        javax.json.JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (OrderedProduct product : products) {
            arrayBuilder.add(runOn(product));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(OrderedProduct orderedProduct) {
        if (this.shouldAddProduct) {
            this.rootObject.add("product", productBuilder.runOn(orderedProduct.getProduct()));
        }

        if (this.shouldAddQuantity) {
            this.rootObject.add("quantity", orderedProduct.getQuantity());
        }

        return this.rootObject.build();
    }
}
