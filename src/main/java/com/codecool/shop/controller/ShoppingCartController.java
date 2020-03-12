package com.codecool.shop.controller;


import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCardDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends JsonResponseController {
    private ShoppingCartDao shoppingCard = ShoppingCardDaoMem.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = super.getIdFrom(req);
        Product product = ProductDaoMem.getInstance().find(id);
        if(product != null) {
            shoppingCard.add(product);
        }
        List<Product> products = shoppingCard.getAll();
        super.jsonify("{\"status\":200, \"message\": {\"shopping_cart_item_count\": " + products.size() + "}}", req, resp);
    }
}
