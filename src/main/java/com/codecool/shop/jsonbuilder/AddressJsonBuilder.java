package com.codecool.shop.jsonbuilder;

import com.codecool.shop.model.Address;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

public class AddressJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddId;
    private boolean shouldAddCountry;
    private boolean shouldAddCity;
    private boolean shouldAddZipCode;
    private boolean shouldAddAddress;

    private AddressJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static AddressJsonBuilder create() {
        return new AddressJsonBuilder();
    }

    public AddressJsonBuilder addId() {
        this.shouldAddId = true;
        return this;
    }

    public AddressJsonBuilder addCountry() {
        this.shouldAddCountry = true;
        return this;
    }

    public AddressJsonBuilder addCity() {
        this.shouldAddCity = true;
        return this;
    }

    public AddressJsonBuilder addZipCode() {
        this.shouldAddZipCode = true;
        return this;
    }

    public AddressJsonBuilder addAddress() {
        this.shouldAddAddress = true;
        return this;
    }

    public JsonArray runOn(List<Address> addresses) {
        javax.json.JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Address address : addresses) {
            arrayBuilder.add(runOn(address));
        }
        return arrayBuilder.build();
    }

    public JsonObject runOn(Address address) {
        if (this.shouldAddId) {
            this.rootObject.add("id", address.getId());
        }

        if (this.shouldAddCountry) {
            this.rootObject.add("country", address.getCountry());
        }

        if (this.shouldAddCity) {
            this.rootObject.add("city", address.getCity());
        }

        if (this.shouldAddZipCode) {
            this.rootObject.add("zip_code", address.getZipCode());
        }

        if (this.shouldAddAddress) {
            this.rootObject.add("address", address.getAddress());
        }

        return this.rootObject.build();
    }
}
