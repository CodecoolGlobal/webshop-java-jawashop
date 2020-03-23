package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.jsonbuilder.CategoryJsonBuilder;
import com.codecool.shop.model.ProductCategory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends JsonResponseController {
    private final ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<ProductCategory> categories = productCategoryDao.getAll();

        super.jsonify(
                CategoryJsonBuilder.create()
                    .addId()
                    .addName()
                    .runOn(categories)
                , req, resp);
    }
}
