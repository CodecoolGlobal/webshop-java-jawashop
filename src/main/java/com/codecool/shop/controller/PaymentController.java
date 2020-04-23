package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.JDBC.CreditCardDaoJDBC;
import com.codecool.shop.dao.JDBC.OrderDaoJDBC;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
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
public class PaymentController extends AuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, InternalServerException, UnAuthorizedException {
        super.doPost(request, response);

        JsonObject postData = super.getPostData(request);
        JsonArrayBuilder errorBag = Json.createArrayBuilder();

        if (postData.getString("type").equals("paypal")) {
            errorBag.add("Payment with PayPal is not supported yet!");
            super.jsonify(errorBag.build(), request, response);
            return;
        }

        String orderID = postData.getString("orderID");
        OrderDao orderDao = new OrderDaoJDBC();

        try {
            if (!orderDao.isExists(orderID)) {
                errorBag.add("The order doesn't exists!");
            }
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your payment details because of some technical difficulties on our side. " +
                "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        CreditCardDao creditCardDao = new CreditCardDaoJDBC();
        CreditCard creditCard = createCreditCardFrom(postData);

        try {
            creditCardDao.add(creditCard);
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your credit card details because of some technical difficulties on our side. " +
                    "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        super.jsonify(errorBag.build(), request, response);
    }

    private CreditCard createCreditCardFrom(JsonObject postData) {
        return new CreditCard(
                Long.parseLong(postData.getString("card_number").replace(" ", "")),
                postData.getString("card_owner"),
                postData.getString("card_expire"),
                Integer.parseInt(postData.getString("card_code")));
    }
}
