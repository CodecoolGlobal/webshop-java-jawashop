package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.SupplierDaoJDBC;
import com.codecool.shop.jsonbuilder.SupplierJsonBuilder;
import com.codecool.shop.model.Supplier;

import javax.json.JsonArray;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/supplier"})
public class SupplierController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        List<Supplier> suppliers = new SupplierDaoJDBC().getAll();

        JsonArray jsonArray = SupplierJsonBuilder.create()
                .addId()
                .addName()
                .runOn(suppliers);

        super.jsonify(jsonArray, req, resp);
    }
}
