package com.codecool.shop.controller;


import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;


public abstract class JsonResponseController extends HttpServlet {
    protected void jsonify(String json, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    protected String getIdFrom(HttpServletRequest req) throws IOException {
        String requestBody = req.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        JsonReader jsonReader = Json.createReader(new StringReader(requestBody));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object.getString("id");
    }
}
