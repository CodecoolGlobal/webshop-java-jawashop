package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.AddressDaoJDBC;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.jsonbuilder.AddressJsonBuilder;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.User;

import javax.json.JsonArray;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/address")
public class AddressController extends AuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        User user = authenticate(request);

        AddressDaoJDBC addressDao = new AddressDaoJDBC();
        List<Address> addresses;
        try {
            addresses = addressDao.getAllBy(user);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

        JsonArray jsonArray = AddressJsonBuilder.create()
                .addId()
                .addCountry()
                .addCity()
                .addZipCode()
                .addAddress()
                .runOn(addresses);

        super.jsonify(jsonArray, request, resp);
    }
}
