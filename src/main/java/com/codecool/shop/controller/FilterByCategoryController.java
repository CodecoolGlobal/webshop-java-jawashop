package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products-by-category"})
public class FilterByCategoryController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();
    private final ProductDao productDataStore = ProductDaoMem.getInstance();
    private final List<Product> products = productDataStore.getAll();
    private final ProductCategoryDaoMem productCategoryDao = ProductCategoryDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        List<Product> productsByCategoryId = productCategoryDao.getProductsByCategoryId(id, products);
        super.jsonify(jsonConverter.productToString(productsByCategoryId), req, resp);
    }
}
