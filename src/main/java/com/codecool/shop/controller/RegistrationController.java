package com.codecool.shop.controller;

import com.codecool.shop.InputValidator;
import com.codecool.shop.dao.JDBC.UserDaoJDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import com.codecool.shop.service.BCryptService;
import com.codecool.shop.service.CryptoService;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationController extends JsonResponseController {

    private final InputValidator validator;
    private final CryptoService cryptoService;

    public RegistrationController() throws IOException {
        validator = new InputValidator();
        cryptoService = new BCryptService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject postData = super.getPostData(req);

        JsonArrayBuilder errorBag = validate(postData);

        UserDao userDao = new UserDaoJDBC();
        User newUser = createOrderFrom(postData);

        try {
            if (userDao.isExists(newUser.getEmail())) {
                errorBag.add("This email address is already in use! Please choose an another one.");
            } else {
                userDao.add(newUser);
            }
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your registration because of some technical difficulties on our side. " +
                    "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        super.jsonify(errorBag.build(), req, resp);
    }

    private JsonArrayBuilder validate(JsonObject postData) {
        JsonArrayBuilder errors = Json.createArrayBuilder();

        if (postData.getString("name", "").isEmpty() || !validator.isValidFullName(postData.getString("name"))) {
            errors.add("Invalid Name!");
        }

        if (postData.getString("email", "").isEmpty() || !validator.isValidEmail(postData.getString("email"))) {
            errors.add("Invalid Email address!");
        }

        if (postData.getString("password", "").isEmpty() || !validator.isValidPassword(postData.getString("password"))) {
            errors.add("Invalid password! It should be at least 8 characters long!");
        }

        return errors;
    }

    private User createOrderFrom(JsonObject postData) {
        return new User(
                postData.getString("name"),
                postData.getString("email"),
                cryptoService.hash(postData.getString("password")));
    }
}
