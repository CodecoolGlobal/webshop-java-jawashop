package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends JsonResponseController {
    private final ProductDao productDataStore = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Product> products = productDataStore.getAll();

        super.jsonify(
                ProductJsonBuilder.create()
                    .addId()
                    .addName()
                    .addDescription()
                    .addPrice()
                    .addCurrency(CurrencyJsonBuilder.create()
                        .addDisplayName())
                    .runOn(products)
                , req, resp);
    }
}
