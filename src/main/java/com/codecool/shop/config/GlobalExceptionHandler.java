package com.codecool.shop.config;

import com.codecool.shop.controller.JsonResponseController;
import com.codecool.shop.exception.HttpException;
import com.codecool.shop.exception.InternalServerException;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebListener()
public class GlobalExceptionHandler extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendExceptionAsJson(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendExceptionAsJson(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendExceptionAsJson(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sendExceptionAsJson(request, response);
    }

    private void sendExceptionAsJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");

        int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String message = InternalServerException.MESSAGE;

        if (isServletException(throwable)) {
            ServletException servletException = (ServletException) throwable;

            Throwable rootCause = servletException.getRootCause();

            if (isHttpException(rootCause)) {
                HttpException httpException = (HttpException) rootCause;
                statusCode = httpException.getStatusCode();
                message = httpException.getMessage();
            }
        }

        response.setContentType("application/json");
        response.setStatus(statusCode);

        PrintWriter out = response.getWriter();
        out.print(Json.createObjectBuilder()
                .add("status", statusCode)
                .add("message", message)
                .build()
                .toString());
        out.close();
    }

    private boolean isServletException(Throwable t) {
        try {
            if (t == null) {
                return false;
            }
            return ServletException.class.isAssignableFrom(t.getClass());
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean isHttpException(Throwable t) {
        try {
            if (t == null) {
                return false;
            }
            return HttpException.class.isAssignableFrom(t.getClass());
        } catch (ClassCastException e) {
            return false;
        }
    }
}
