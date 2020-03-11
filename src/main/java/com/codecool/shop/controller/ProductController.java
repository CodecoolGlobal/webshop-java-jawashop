package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import com.codecool.shop.model.Product;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();
    private final ProductDao productDataStore = ProductDaoMem.getInstance();
    private final ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDataStore.getAll();

        super.jsonify(jsonConverter.toString(products), req, resp);
    }
}
