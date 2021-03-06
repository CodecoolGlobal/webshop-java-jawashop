package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.UserDaoJDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class AuthenticatedController extends JsonResponseController {

    protected static final String COOKIE_KEY = "auth_token";

    protected User authenticate(HttpServletRequest request) throws InternalServerException, UnAuthorizedException {
        Optional<Cookie> optionalAuthCookie = getAuthCookie(request);

        if (!optionalAuthCookie.isPresent()) {
            throw new UnAuthorizedException();
        }

        UserDao userDao = new UserDaoJDBC();

        try {
            Optional<User> optionalUser = userDao.findBy(optionalAuthCookie.get().getValue());
            if (!optionalUser.isPresent()) {
                throw new UnAuthorizedException();
            }
            return optionalUser.get();
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    protected Optional<Cookie> getAuthCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(COOKIE_KEY)) {
                return Optional.of(cookie);
            }
        }

        return Optional.empty();
    }
}
