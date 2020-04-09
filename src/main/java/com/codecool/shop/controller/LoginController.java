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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/login")
public class LoginController extends JsonResponseController {

    private final InputValidator validator;
    private final CryptoService cryptoService;

    public LoginController() throws IOException {
        validator = new InputValidator();
        cryptoService = new BCryptService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JsonObject postData = super.getPostData(req);

        JsonArrayBuilder errorBag = validate(postData);

        UserDao userDao = new UserDaoJDBC();

        try {
            String password = cryptoService.hash(postData.getString("password"));
            Optional<User> optionalUser = userDao.find(postData.getString("email"), password);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setAuthToken(cryptoService.hash(UUID.randomUUID().toString()));
                resp.addCookie(new Cookie("auth_token", user.getAuthToken()));
                userDao.update(user);
            } else {
                errorBag.add("Wrong email address or password!");
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

        if (postData.getString("email", "").isEmpty() || !validator.isValidEmail(postData.getString("email"))) {
            errors.add("Invalid Email address!");
        }

        if (postData.getString("password", "").isEmpty() || !validator.isValidPassword(postData.getString("password"))) {
            errors.add("Invalid password! It should be at least 8 characters long!");
        }

        return errors;
    }
}
