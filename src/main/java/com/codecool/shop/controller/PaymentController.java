package com.codecool.shop.controller;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/payment")
public class PaymentController extends JsonResponseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject formData = super.getPostData(request);
        super.jsonify(Json.createArrayBuilder().build(), request, response);
    }
}
