package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.jsonbuilder.CategoryJsonBuilder;

import javax.json.JsonArray;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ProductCategory> categories = new ProductCategoryDaoJDBC().getAll();

        JsonArray jsonArray = CategoryJsonBuilder.create()
                .addId()
                .addName()
                .runOn(categories);

        super.jsonify(jsonArray, req, resp);
    }
}
