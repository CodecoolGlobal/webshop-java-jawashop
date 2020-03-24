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
    private final ProductDaoMem products = ProductDaoMem.getInstance();

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
