package com.codecool.shop.controller;


import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJDBC;
import com.codecool.shop.model.Product;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class ShoppingCartController extends JsonResponseController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = super.getIdFrom(req);
        Product product = new ProductDaoJDBC().find(id);
        if(product != null) {
            new ShoppingCartDaoJDBC().add(product);
        }
        List<Product> shoppingCartProducts = new ShoppingCartDaoJDBC().getAll();
        float totalPrice = 0;
        for (Product shoppingCartProduct : shoppingCartProducts) {
            totalPrice += shoppingCartProduct.getDefaultPrice();
        }

        String totalValue = totalPrice + " " + new ProductDaoJDBC().getAll().get(0).getDefaultCurrency().toString();

        super.jsonify("{\"status\":200, \"message\": {\"cart\": {\"item_count\": " + shoppingCartProducts.size() + ", \"total_value\": \"" + totalValue + "\"}}}", req, resp);
    }
}
