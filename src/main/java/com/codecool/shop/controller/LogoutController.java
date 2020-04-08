package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.UserDaoJDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/logout")
public class LogoutController extends AuthenticatedController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, InternalServerException {
        Optional<Cookie> optionalAuthCookie = super.getAuthCookie(request);

        if (!optionalAuthCookie.isPresent()) {
            super.jsonify("Successful logout", request, response);
            return;
        }

        Cookie authCookie = optionalAuthCookie.get();

        authCookie.setMaxAge(0);
        response.addCookie(authCookie);

        UserDao userDao = new UserDaoJDBC();

        try {
            Optional<User> optionalUser = userDao.findBy(authCookie.getValue());

            if (optionalUser.isPresent()) {
                User loggedInUser = optionalUser.get();
                loggedInUser.setAuthToken(null);
                userDao.update(loggedInUser);
            }

            super.jsonify("Successful logout", request, response);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }
}
