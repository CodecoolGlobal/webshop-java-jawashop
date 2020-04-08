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

    private void tryAuthenticate(HttpServletRequest request) throws UnAuthorizedException, InternalServerException {
        Optional<String> optionalAuthCookie = getAuthCookie(request);

        if (!optionalAuthCookie.isPresent()) {
            throw new UnAuthorizedException();
        }

        UserDao userDao = new UserDaoJDBC();

        try {
            if (!userDao.isAuthTokenExists(optionalAuthCookie.get())) {
                throw new UnAuthorizedException();
            }
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    private Optional<String> getAuthCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return Optional.empty();
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("auth_token")) {
                return Optional.of(cookie.getValue());
            }
        }

        return Optional.empty();
    }
}
