package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.UserDaoJDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/auth")
public class AuthorizationController extends AuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws UnAuthorizedException, InternalServerException, IOException {
        Optional<Cookie> optionalAuthCookie = getAuthCookie(request);
        Cookie cookie = new Cookie("auth_token", "");
        cookie.setMaxAge(0);

        if (!optionalAuthCookie.isPresent()) {
            resp.addCookie(cookie);
            jsonify("FALSE", request, resp);
            return;
        }

        UserDao userDao = new UserDaoJDBC();

        try {
            Optional<User> optionalUser = userDao.findBy(optionalAuthCookie.get().getValue());
            if (!optionalUser.isPresent()) {
                resp.addCookie(cookie);
                jsonify("FALSE", request, resp);
            }
            jsonify("OK", request, resp);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }
}
