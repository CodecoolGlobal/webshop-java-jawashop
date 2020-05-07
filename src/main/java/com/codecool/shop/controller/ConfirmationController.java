package com.codecool.shop.controller;

import com.codecool.shop.dao.JDBC.*;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.exception.UnAuthorizedException;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.OrderStatus;
import com.codecool.shop.model.User;

import javax.json.JsonObject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/confirm")
public class ConfirmationController extends AuthenticatedController {

    private final Random random;

    public ConfirmationController() {
        this.random = new Random();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, InternalServerException, UnAuthorizedException {
        User user = super.authenticate(req);

        JsonObject postData = super.getPostData(req);
        OrderStatusDaoJDBC orderStatusDao = new OrderStatusDaoJDBC();
        OrderStatus orderStatus = OrderStatus.asConfirmed();
        if (random.nextInt(10) < 5) {
            orderStatus = OrderStatus.asShipped();
        }
        orderStatus = orderStatusDao.get(orderStatus);
        if (orderStatus == null) {
            throw new InternalServerException(null);
        }

        Order order = new Order(
                postData.getString("id"),
                orderStatus,
                null);

        OrderDao orderDao = new OrderDaoJDBC();
        if (!orderDao.has(user, order)) {
            throw new InternalServerException(null);
        }
        orderDao.updateStatus(order);

        super.jsonify("OK", req, resp);
    }
}
