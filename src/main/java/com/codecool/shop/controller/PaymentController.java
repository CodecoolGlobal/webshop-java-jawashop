package com.codecool.shop.controller;

import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.dao.JDBC.CreditCardDaoJDBC;
import com.codecool.shop.dao.JDBC.OrderDaoJDBC;
import com.codecool.shop.dao.JDBC.OrderedProductJDBC;
import com.codecool.shop.dao.JDBC.PaymentDaoJDBC;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.dao.PaymentDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.jsonbuilder.CurrencyJsonBuilder;
import com.codecool.shop.jsonbuilder.OrderedProductJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.model.*;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends AuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, InternalServerException, UnAuthorizedException {
        User user = super.authenticate(request);

        JsonObject postData = super.getPostData(request);
        JsonArrayBuilder errorBag = Json.createArrayBuilder();

        if (postData.getString("type").equals("paypal")) {
            errorBag.add("Payment with PayPal is not supported yet!");
            super.jsonify(errorBag.build(), request, response);
            return;
        }

        Order order = new Order(postData.getString("order_id"), user, null, null, 0, null, null);
        OrderDao orderDao = new OrderDaoJDBC();

        try {
            System.out.printf("Checking order.id = %s", order.getId());
            if (!orderDao.isExists(order.getId())) {
                errorBag.add("The order doesn't exists!");
            }
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your payment details because of some technical difficulties on our side. " +
                "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        CreditCardDao creditCardDao = new CreditCardDaoJDBC();
        PaymentDao paymentDao = new PaymentDaoJDBC();
        CreditCard creditCard = createCreditCardFrom(postData);

        try {
            creditCardDao.add(creditCard);
            paymentDao.add(new Payment(order, creditCard));
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't save Your payment details because of some technical difficulties on our side. " +
                    "We will investigate the issue! Thank You for your patience!");
            e.printStackTrace();
        }

        OrderedProductDao orderedProductDao = new OrderedProductJDBC();
        List<OrderedProduct> productList = new ArrayList<>();

        try {
            productList = orderedProductDao.findBy(order);
        } catch (SQLException e) {
            errorBag.add("Sorry but we couldn't return Your order details because of some technical difficulties on our side. " +
                    "We will investigate the issue! Thank You for your patience!");
            super.jsonify(errorBag.build(), request, response);
            return;
        }

        JsonArray productsJsonArray = OrderedProductJsonBuilder.create()
                .addProduct(ProductJsonBuilder.create()
                        .addId()
                        .addName()
                        .addDescription()
                        .addPrice()
                        .addCurrency(CurrencyJsonBuilder.create()
                            .addDisplayName()))
                .addQuantity()
                .runOn(productList);

        super.jsonify(productsJsonArray, request, response);
    }

    private CreditCard createCreditCardFrom(JsonObject postData) {
        return new CreditCard(
                Long.parseLong(postData.getString("card_number").replace(" ", "")),
                postData.getString("card_owner"),
                postData.getString("card_expire"),
                Integer.parseInt(postData.getString("card_code")));
    }
}