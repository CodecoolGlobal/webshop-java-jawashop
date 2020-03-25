package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJDBC;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.jsonbuilder.SupplierJsonBuilder;
import com.codecool.shop.model.Product;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> shoppingCartProducts = new ShoppingCartDaoJDBC().getAll();

        JsonObjectBuilder cartBuilder = calculateCartStats(shoppingCartProducts);

        if (req.getParameter("list") != null) {
            cartBuilder.add(
                    "products",
                    ProductJsonBuilder.create()
                            .addId()
                            .addName()
                            .addDescription()
                            .addPrice()
                            .addCurrency(CurrencyJsonBuilder.create()
                                    .addSymbol()
                                    .addDisplayName())
                            .addSupplier(SupplierJsonBuilder.create()
                                    .addName())
                            .runOn(shoppingCartProducts));
        }

        super.jsonify(cartBuilder.build(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = super.getIdFrom(req);
        Product product = new ProductDaoJDBC().find(id);

        if(product != null) {
            new ShoppingCartDaoJDBC().add(product);
        }

        List<Product> shoppingCartProducts = new ShoppingCartDaoJDBC().getAll();

        JsonObjectBuilder rootObject = calculateCartStats(shoppingCartProducts);

        super.jsonify(rootObject.build(), req, resp);
    }

    private JsonObjectBuilder calculateCartStats(List<Product> shoppingCartProducts) {
        float totalPrice = 0;

        for (Product product : shoppingCartProducts) {
            totalPrice += product.getDefaultPrice();
        }

        String totalValue = totalPrice + " " + new ProductDaoJDBC().getAll().get(0).getDefaultCurrency().toString();

        JsonObjectBuilder cartBuilder = Json.createObjectBuilder();
        cartBuilder
                .add("item_count", shoppingCartProducts.size())
                .add("total_value", totalValue);

        return cartBuilder;
    }
}
