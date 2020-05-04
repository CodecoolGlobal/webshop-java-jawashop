package com.codecool.shop.controller;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.stream.Collectors;

public abstract class JsonResponseController extends HttpServlet {

    protected void jsonify(JsonObject jsonObject, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.print(wrap(jsonObject));
        out.flush();
        out.close();
    }

    protected void jsonify(JsonArray jsonArray, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.print(wrap(jsonArray));
        out.flush();
        out.close();
    }

    protected void jsonify(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        PrintWriter out = response.getWriter();
        out.print(wrap(message));
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

    protected JsonObject getPostData(HttpServletRequest request) throws IOException {
        String requestBody = request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));

        JsonReader jsonReader = Json.createReader(new StringReader(requestBody));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }

    private String wrap(JsonObject rootObject) {
        return Json.createObjectBuilder()
                .add("status", 200)
                .add("message", rootObject)
                .build()
                .toString();
    }

    private String wrap(JsonArray rootArray) {
        return Json.createObjectBuilder()
                .add("status", 200)
                .add("message", rootArray)
                .build()
                .toString();
    }

    private String wrap(String value) {
        return Json.createObjectBuilder()
                .add("status", 200)
                .add("message", value)
                .build()
                .toString();
    }
}
