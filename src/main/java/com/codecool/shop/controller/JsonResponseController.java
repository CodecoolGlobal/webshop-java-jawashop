package com.codecool.shop.controller;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public abstract class JsonResponseController extends HttpServlet {
    protected void jsonify(String json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }
}
