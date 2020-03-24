package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products-by-category"})
public class FilterByCategoryController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        ProductCategory productCategory = new ProductCategoryDaoJDBC().find(req.getParameter("id"));
        List<Product> productsByCategoryId = new ProductDaoJDBC().getBy(productCategory);
        super.jsonify(jsonConverter.productToString(productsByCategoryId), req, resp);
    }
}
