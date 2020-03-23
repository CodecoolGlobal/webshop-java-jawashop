package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends JsonResponseController {
    ProductDao productDao;
    private final JsonConverter jsonConverter = new JsonConverter();
    private final ProductDao productDataStore = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            DataSource dataSource = new DbConnection().getConnection();
            productDao = new ProductDaoJDBC(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Product> products = productDao.getAll();
        super.jsonify(jsonConverter.productToString(products), req, resp);
    }
}
