package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.JDBC.CreditCardDaoJDBC;
import com.codecool.shop.model.CreditCard;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends JsonResponseController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("doPost");
        JsonObject postData = super.getPostData(request);

        if (postData.getString("type").equals("paypal")) {
            super.jsonify(Json.createArrayBuilder().build(), request, response);
            return;
        }

        CreditCardDao creditCardDao = new CreditCardDaoJDBC();
        JsonArrayBuilder errorBag = Json.createArrayBuilder();

        try {
            creditCardDao.add(createCreditCardFrom(postData));
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your credit card details because of some technical difficulties on our side. " +
                    "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        super.jsonify(errorBag.build(), request, response);
    }

    private CreditCard createCreditCardFrom(JsonObject postData) {
        return new CreditCard(
                // JSON parser can't handle Long                            (see js/payment/logic.js)
                Long.parseLong(postData.getString("card_number").replace(" ", "")),
                postData.getString("card_owner"),
                postData.getString("card_expire"),
                postData.getInt("card_code"));
    }
}
