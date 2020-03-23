package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.model.Product;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
//            DataSource dataSource =  DbConnection.getConnection();
            ProductDao productDao = new ProductDaoJDBC();
            List<Product> products = productDao.getAll();
            super.jsonify(jsonConverter.productToString(products), req, resp);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
