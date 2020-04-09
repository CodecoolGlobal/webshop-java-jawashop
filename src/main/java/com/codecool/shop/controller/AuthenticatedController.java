package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.UserDaoJDBC;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class AuthenticatedController extends JsonResponseController {

    protected static final String COOKIE_KEY = "auth_token";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        tryAuthenticate(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws UnAuthorizedException, InternalServerException, IOException {
        tryAuthenticate(req);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws UnAuthorizedException, InternalServerException, IOException {
        tryAuthenticate(req);
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

    private void tryAuthenticate(HttpServletRequest request) throws UnAuthorizedException, InternalServerException {
        Optional<Cookie> optionalAuthCookie = getAuthCookie(request);

        if (!optionalAuthCookie.isPresent()) {
            throw new UnAuthorizedException();
        }

        UserDao userDao = new UserDaoJDBC();

        try {
            if (!userDao.isAuthTokenExists(optionalAuthCookie.get().getValue())) {
                throw new UnAuthorizedException();
            }
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }
}
