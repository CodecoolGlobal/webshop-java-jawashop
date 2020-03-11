package com.codecool.shop.controller;

import com.codecool.shop.JsonConverter;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/supplier"})
public class SupplierController extends JsonResponseController {
    private final JsonConverter jsonConverter = new JsonConverter();
    private final SupplierDao supplierDao = SupplierDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Supplier> suppliers = supplierDao.getAll();
        super.jsonify(jsonConverter.supplierToString(suppliers), req, resp);
    }
}
