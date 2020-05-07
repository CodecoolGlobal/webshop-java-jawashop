package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.Order;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class OrderJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddId;
    private boolean shouldAddName;
    private boolean shouldAddEmail;
    private boolean shouldAddPhoneNumber;
    private boolean shouldAddBillingAddress;
    private AddressJsonBuilder billingAddressBuilder;
    private boolean shouldAddShippingAddress;
    private AddressJsonBuilder shippingAddressBuilder;
    private boolean shouldAddTotalPrice;
    private boolean shouldAddProducts;
    private OrderedProductJsonBuilder orderedProductBuilder;
    private boolean shouldAddDate;
    private boolean shouldAddStatus;

    private OrderJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static OrderJsonBuilder create() {
        return new OrderJsonBuilder();
    }

    public OrderJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public OrderJsonBuilder addName() {
        this.shouldAddName = true;
        return this;
    }

    public OrderJsonBuilder addEmail() {
        this.shouldAddEmail = true;
        return this;
    }

    public OrderJsonBuilder addPhoneNumber() {
        this.shouldAddPhoneNumber = true;
        return this;
    }

    public OrderJsonBuilder addBillingAddress(AddressJsonBuilder addressBuilder) {
        this.shouldAddBillingAddress = true;
        this.billingAddressBuilder = addressBuilder;
        return this;
    }

    public OrderJsonBuilder addShippingAddress(AddressJsonBuilder addressBuilder) {
        this.shouldAddShippingAddress = true;
        this.shippingAddressBuilder = addressBuilder;
        return this;
    }

    public OrderJsonBuilder addTotalPrice() {
        this.shouldAddTotalPrice = true;
        return this;
    }

    public OrderJsonBuilder addProducts(OrderedProductJsonBuilder orderedProductBuilder) {
        this.shouldAddProducts = true;
        this.orderedProductBuilder = orderedProductBuilder;
        return this;
    }

    public OrderJsonBuilder addStatus() {
        this.shouldAddStatus = true;
        return this;
    }

    public OrderJsonBuilder addDate() {
        this.shouldAddDate = true;
        return this;
    }

    public JsonArray runOn(List<Order> orders) {
        javax.json.JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Order order : orders) {
            arrayBuilder.add(runOn(order));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(Order order) {
        if (this.shouldAddId) {
            this.rootObject.add("id", order.getId());
        }

        if (this.shouldAddName) {
            this.rootObject.add("name", order.getName());
        }

        if (this.shouldAddEmail) {
            this.rootObject.add("email", order.getEmail());
        }

        if (this.shouldAddPhoneNumber) {
            this.rootObject.add("phone_number", order.getPhoneNumber());
        }

        if (shouldAddBillingAddress) {
            this.rootObject.add("billing_address", billingAddressBuilder.runOn(order.getBillingAddress()));
        }

        if (shouldAddShippingAddress) {
            this.rootObject.add("shipping_address", shippingAddressBuilder.runOn(order.getShippingAddress()));
        }

        if (shouldAddTotalPrice) {
            this.rootObject.add("total_price", order.getTotalPrice());
        }

        if (shouldAddStatus) {
            this.rootObject.add("status", order.getStatus().getName());
        }

        if (shouldAddDate) {
            this.rootObject.add("date", order.getDate());
        }

        if (shouldAddProducts) {
            this.rootObject.add("items", orderedProductBuilder.runOn(order.getProducts()));
        }

        return this.rootObject.build();
    }
}
