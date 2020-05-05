package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.JDBC.ProductDaoJDBC;
import com.codecool.shop.jsonbuilder.*;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products-by-category"})
public class FilterByCategoryController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductCategory productCategory = new ProductCategoryDaoJDBC().find(req.getParameter("id"));

        List<Product> productsByCategoryId = new ProductDaoJDBC().getBy(productCategory);

        JsonArray jsonArray = ProductJsonBuilder.create()
                .addId()
                .addName()
                .addDescription()
                .addPrice()
                .addCurrency(CurrencyJsonBuilder.create()
                        .addDisplayName())
                .addSupplier(SupplierJsonBuilder.create()
                        .addName())
                .runOn(productsByCategoryId);

        super.jsonify(jsonArray, req, resp);
    }
}
