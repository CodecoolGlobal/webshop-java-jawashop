package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJDBC;
import com.codecool.shop.model.ProductCategory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
            List<ProductCategory> categories = productCategoryDao.getAll();
            super.jsonify(jsonConverter.categoryToString(categories), req, resp);

    }
}
