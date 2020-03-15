package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCardDaoMem;
import com.codecool.shop.model.Product;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends JsonResponseController {
    private ShoppingCartDao shoppingCard = ShoppingCardDaoMem.getInstance();
    private final ProductDaoMem products = ProductDaoMem.getInstance();
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> shoppingCartProducts = shoppingCard.getAll();
        float totalPrice = 0;

        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();
        JsonObjectBuilder messageBuilder = Json.createObjectBuilder();
        JsonObjectBuilder cartBuilder = Json.createObjectBuilder();
        JsonArrayBuilder productsBuilder = Json.createArrayBuilder();

        for (Product product : shoppingCartProducts) {
            JsonObjectBuilder productBuilder = Json.createObjectBuilder();
            JsonObject productJson = productBuilder
                    .add("id", product.getId())
                    .add("name", product.getName())
                    .add("description", product.getDescription())
                    .add("price", product.getPrice())
                    .add("currency", product.getDefaultCurrency().getSymbol())
                    .add("supplier_name", product.getSupplier().getName())
                    .build();

            productsBuilder.add(productJson);
            totalPrice += product.getDefaultPrice();
        }

        String totalValue = totalPrice + " " + products.getAll().get(0).getDefaultCurrency().toString();

        cartBuilder
                .add("item_count", shoppingCartProducts.size())
                .add("total_value", totalValue);

        if (req.getParameter("list") != null) {
            cartBuilder.add("products", productsBuilder);
        }

        messageBuilder.add("cart", cartBuilder);

        rootBuilder
                .add("status", 200)
                .add("message" , messageBuilder);

        super.jsonify(rootBuilder.build().toString(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = super.getIdFrom(req);
        Product product = products.find(id);
        if(product != null) {
            shoppingCard.add(product);
        }
        List<Product> shoppingCartProducts = shoppingCard.getAll();
        float totalPrice = 0;
        for (Product shoppingCartProduct : shoppingCartProducts) {
            totalPrice += shoppingCartProduct.getDefaultPrice();
        }

        String totalValue = totalPrice + " " + products.getAll().get(0).getDefaultCurrency().toString();

        super.jsonify("{\"status\":200, \"message\": {\"cart\": {\"item_count\": " + shoppingCartProducts.size() + ", \"total_value\": \"" + totalValue + "\"}}}", req, resp);
    }
}
