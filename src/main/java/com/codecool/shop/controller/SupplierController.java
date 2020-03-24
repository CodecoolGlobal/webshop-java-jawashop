package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/supplier"})
public class SupplierController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        try {
            SupplierDao supplierDao = new SupplierDaoJDBC();
            List<Supplier> suppliers = supplierDao.getAll();
            super.jsonify(jsonConverter.supplierToString(suppliers), req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
