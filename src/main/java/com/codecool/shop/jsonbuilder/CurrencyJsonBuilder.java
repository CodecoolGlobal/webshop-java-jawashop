package com.codecool.shop.jsonbuilder;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Currency;

public class CurrencyJsonBuilder {

    private final JsonObjectBuilder rootObject;
    private boolean shouldAddSymbol;
    private boolean shouldAddDisplayName;

    private CurrencyJsonBuilder() {
        this.rootObject = Json.createObjectBuilder();
    }

    public static CurrencyJsonBuilder create() {
        return new CurrencyJsonBuilder();
    }

    public CurrencyJsonBuilder addSymbol() {
        this.shouldAddSymbol = true;
        return this;
    }

    public CurrencyJsonBuilder addDisplayName() {
        this.shouldAddDisplayName = true;
        return this;
    }

    public JsonObject runOn(Currency currency) {
        if (this.shouldAddSymbol) {
            this.rootObject.add("symbol", currency.getSymbol());
        }

        if (this.shouldAddDisplayName) {
            this.rootObject.add("code", currency.getCurrencyCode());
        }

        return this.rootObject.build();
    }
}
