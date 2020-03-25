package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.model.Product;

import javax.json.JsonArray;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = new ProductDaoJDBC().getAll();

        JsonArray jsonArray = ProductJsonBuilder.create()
                .addId()
                .addName()
                .addDescription()
                .addPrice()
                .addCurrency(CurrencyJsonBuilder.create()
                        .addDisplayName())
                .runOn(products);

        super.jsonify(jsonArray, req, resp);
    }
}
