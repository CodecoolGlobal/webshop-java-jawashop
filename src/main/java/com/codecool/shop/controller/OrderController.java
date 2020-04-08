package com.codecool.shop.controller;

import com.codecool.shop.InputValidator;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.JDBC.AddressDaoJDBC;
import com.codecool.shop.dao.JDBC.OrderDaoJDBC;
import com.codecool.shop.dao.JDBC.OrderedProductJDBC;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoJDBC;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderedProduct;

import javax.json.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderController extends AuthenticatedController {

    private static final InputValidator validator;

    static {
        validator = new InputValidator();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, InternalServerException, UnAuthorizedException {
        super.doPost(req, resp);

        JsonObject postData = super.getPostData(req);
        JsonArray errorBag = validate(postData);

        if (!errorBag.isEmpty()) {
            super.jsonify(errorBag, req, resp);
            return;
        }

        Order order = createOrderFrom(postData);

        AddressDao addressDao = new AddressDaoJDBC();
        addressDao.add(order.getBillingAddress());
        addressDao.add(order.getShippingAddress());

        OrderDao orderDao = new OrderDaoJDBC();
        orderDao.add(order);

        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoJDBC();
        List<CartItem> cartItems = shoppingCartDao.getAll();

        OrderedProductDao orderedProductDao = new OrderedProductJDBC();

        for (CartItem cartItem : cartItems) {
            orderedProductDao.add(new OrderedProduct(order, cartItem.getProduct(), cartItem.getQuantity()));
            shoppingCartDao.remove(cartItem);
        }

        super.jsonify(Json.createArrayBuilder().build(), req, resp);
    }

    private JsonArray validate(JsonObject postData) {
        JsonArrayBuilder errors = Json.createArrayBuilder();

        if (postData.getString("name", "").isEmpty() || !validator.isValidFullName(postData.getString("name"))) {
            errors.add("Invalid Name!");
        }

        if (postData.getString("email", "").isEmpty() || !validator.isValidEmail(postData.getString("email"))) {
            errors.add("Invalid Email address!");
        }

        if (postData.getString("phoneNumber", "").isEmpty() || !validator.isValidPhoneNumber(postData.getString("phoneNumber"))) {
            errors.add("Invalid phone number!");
        }

        if (postData.getString("billingCountry", "").isEmpty() || !validator.isValidCountry(postData.getString("billingCountry"))) {
            errors.add("Invalid billing country!");
        }

        if (postData.getString("billingCity", "").isEmpty() || !validator.isValidCity(postData.getString("billingCity"))) {
            errors.add("Invalid billing city!");
        }

        if (postData.getString("billingZip", "").isEmpty() || !validator.isValidZipCode(postData.getString("billingZip"))) {
            errors.add("Invalid billing ZIP code!");
        }

        if (postData.getString("billingAddress", "").isEmpty() || !validator.isValidAddress(postData.getString("billingAddress"))) {
            errors.add("Invalid billing address!");
        }

        if (postData.getString("shippingCountry", "").isEmpty() || !validator.isValidCountry(postData.getString("shippingCountry"))) {
            errors.add("Invalid shipping country!");
        }

        if (postData.getString("shippingCity", "").isEmpty() || !validator.isValidCity(postData.getString("shippingCity"))) {
            errors.add("Invalid shipping city!");
        }

        if (postData.getString("shippingZip", "").isEmpty() || !validator.isValidZipCode(postData.getString("shippingZip"))) {
            errors.add("Invalid shipping ZIP code!");
        }

        if (postData.getString("shippingAddress", "").isEmpty() || !validator.isValidAddress(postData.getString("shippingAddress"))) {
            errors.add("Invalid shipping address!");
        }

        return errors.build();
    }

    private Order createOrderFrom(JsonObject postData) {
        return new Order(
                postData.getString("name"),
                postData.getString("email"),
                Long.parseLong(postData.getString("phoneNumber").substring(0, 11)),
                new Address(
                        postData.getString("billingCountry"),
                        postData.getString("billingCity"),
                        postData.getString("billingZip"),
                        postData.getString("billingAddress")),
                new Address(
                    postData.getString("shippingCountry"),
                    postData.getString("shippingCity"),
                    postData.getString("shippingZip"),
                    postData.getString("shippingAddress")));
    }
}
