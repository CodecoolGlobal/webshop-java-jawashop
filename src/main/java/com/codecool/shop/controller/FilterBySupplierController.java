package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoJDBC;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.jsonbuilder.SupplierJsonBuilder;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.json.JsonArray;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/products-by-supplier"})
public class FilterBySupplierController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Supplier supplier = new SupplierDaoJDBC().find(req.getParameter("id"));

        List<Product> productsBySupplierId = new ProductDaoJDBC().getBy(supplier);

        JsonArray jsonArray = ProductJsonBuilder.create()
                .addId()
                .addName()
                .addDescription()
                .addPrice()
                .addCurrency(CurrencyJsonBuilder.create()
                        .addDisplayName())
                .addSupplier(SupplierJsonBuilder.create()
                        .addName())
                .runOn(productsBySupplierId);

        super.jsonify(jsonArray, req, resp);
    }
}
