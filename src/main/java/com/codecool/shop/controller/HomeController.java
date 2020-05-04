package com.codecool.shop.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/"})
public class HomeController extends JsonResponseController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.print(
                "<html>" +
                        "<head>" +
                            "<title>Codecool Shop</title>" +
                            "<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/css/main.css\" />" +
                            "<script src=\"/static/js/core.js\" type=\"module\" defer></script>" +
                        "</head>" +
                        "<body></body>" +
                    "</html>");
        out.flush();
        out.close();
    }
}
