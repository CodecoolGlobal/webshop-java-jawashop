package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.CartItem;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class CartItemJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private ProductJsonBuilder productBuilder;
    private boolean shouldAddId;
    private boolean shouldAddProducts;
    private boolean shouldAddQuantity;

    private CartItemJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static CartItemJsonBuilder create() {
        return new CartItemJsonBuilder();
    }

    public CartItemJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public CartItemJsonBuilder addProducts(ProductJsonBuilder productBuilder) {
        this.shouldAddProducts = true;
        this.productBuilder = productBuilder;
        return this;
    }

    public CartItemJsonBuilder addQuantity() {
        this.shouldAddQuantity = true;
        return this;
    }

    public JsonArray runOn(List<CartItem> cartItems) {
        javax.json.JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (CartItem cartItem : cartItems) {
            arrayBuilder.add(runOn(cartItem));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(CartItem cartItem) {
        if (this.shouldAddId) {
            this.rootObject.add("id", cartItem.getId());
        }

        if (shouldAddProducts) {
            this.rootObject.add("product", productBuilder.runOn(cartItem.getProduct()));
        }

        if (this.shouldAddQuantity) {
            this.rootObject.add("quantity", cartItem.getQuantity());
        }

        return this.rootObject.build();
    }
}
