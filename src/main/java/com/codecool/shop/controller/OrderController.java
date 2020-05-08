package com.codecool.shop.controller;

import com.codecool.shop.InputValidator;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.JDBC.*;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.jsonbuilder.OrderJsonBuilder;
import com.codecool.shop.jsonbuilder.OrderedProductJsonBuilder;
import com.codecool.shop.jsonbuilder.ProductJsonBuilder;
import com.codecool.shop.model.*;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderController extends AuthenticatedController {

    private static final InputValidator validator;

    static {
        validator = new InputValidator();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = authenticate(req);

        OrderDao orderDao = new OrderDaoJDBC();
        List<Order> orders;
        try {
            orders = orderDao.getAllWithProducts(user);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

        JsonArray ordersJson = OrderJsonBuilder.create()
                .addProducts(OrderedProductJsonBuilder.create()
                    .addProduct(ProductJsonBuilder.create()
                        .addName()
                        .addPrice())
                    .addQuantity())
                .addTotalPrice()
                .addStatus()
                .addDate()
                .runOn(orders);

        super.jsonify(ordersJson, req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, InternalServerException, UnAuthorizedException {
        User user = super.authenticate(req);

        JsonObject postData = super.getPostData(req);
        JsonArray errorBag = validate(postData);

        if (!errorBag.isEmpty()) {
            super.jsonify(errorBag, req, resp);
            return;
        }

        OrderStatusDaoJDBC orderStatusDao = new OrderStatusDaoJDBC();
        OrderStatus orderStatus = orderStatusDao.get(OrderStatus.asChecked());
        if (orderStatus == null) {
            throw new InternalServerException(null);
        }


        AddressDao addressDao = new AddressDaoJDBC();
        Address billingAddress;
        try {
            billingAddress = saveOrGet(getBillingAddress(postData, user), addressDao, user);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
        Address shippingAddress;
        try {
            shippingAddress = saveOrGet(getShippingAddress(postData, user), addressDao, user);
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }

        Order order = createOrderFrom(postData, user, orderStatus, billingAddress, shippingAddress);
        OrderDao orderDao = new OrderDaoJDBC();
        orderDao.add(order);

        ShoppingCartDao shoppingCartDao = new ShoppingCartDaoJDBC();
        OrderedProductDao orderedProductDao = new OrderedProductJDBC();
        List<CartItem> cartItems = shoppingCartDao.getAll(user);

        for (CartItem cartItem : cartItems) {
            OrderedProduct orderedProduct = new OrderedProduct(order, cartItem.getProduct(), cartItem.getQuantity());
            orderedProductDao.add(orderedProduct);
            shoppingCartDao.remove(cartItem);
            order.add(orderedProduct);
        }

        JsonObject orderJson = OrderJsonBuilder.create()
                .addId()
                .addTotalPrice()
                .runOn(order);

        super.jsonify(orderJson, req, resp);
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

    private Address getBillingAddress(JsonObject postData, User user) {
        return new Address(
                user,
                postData.getString("billingCountry"),
                postData.getString("billingCity"),
                postData.getString("billingZip"),
                postData.getString("billingAddress"));
    }

    private Address getShippingAddress(JsonObject postData, User user) {
        return new Address(
                user,
                postData.getString("shippingCountry"),
                postData.getString("shippingCity"),
                postData.getString("shippingZip"),
                postData.getString("shippingAddress"));
    }

    private Address saveOrGet(Address address, AddressDao addressDao, User user) throws SQLException {
        Address dbAddress = addressDao.find(address, user);
        if (dbAddress != null) {
            return dbAddress;
        } else {
            addressDao.add(address);
            return address;
        }
    }

    private Order createOrderFrom(JsonObject postData, User user, OrderStatus orderStatus, Address billingAddress, Address shippingAddress) {
        return new Order(
                user,
                orderStatus,
                postData.getString("name"),
                postData.getString("email"),
                Long.parseLong(postData.getString("phoneNumber").substring(0, 11)),
                billingAddress,
                shippingAddress);
    }
}
