package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJDBC implements OrderDao {

    private DataSource dataSource;

    public OrderDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public OrderDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO orders (id, user_id, name, email, phone_number, billing_address_id, shipping_address_id) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, order.getId(), Types.OTHER);
            statement.setObject(2, order.getUser().getId(), Types.OTHER);
            statement.setString(3, order.getName());
            statement.setString(4, order.getEmail());
            statement.setLong(5, order.getPhoneNumber());
            statement.setObject(6, order.getBillingAddress().getId(), Types.OTHER);
            statement.setObject(7, order.getShippingAddress().getId(), Types.OTHER);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExists(String id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM orders WHERE id = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, id, Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public List<Order> getAllWithProducts(User user) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query =
                "SELECT " +
                    "_order.id AS order_id," +
                    "product.name," +
                    "product.default_price," +
                    "op.quantity," +
                    "_order.inserted_at " +
                "FROM orders _order " +
                "LEFT JOIN order_products op on _order.id = op.order_id " +
                "LEFT JOIN product product on op.product_id = product.id " +
                "WHERE _order.user_id = ?; ";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, user.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                if (order == null || !resultSet.getString("order_id").equals(order.getId())) {
                    order = new Order(
                            resultSet.getString("order_id"),
                            resultSet.getString("inserted_at"));
                    orders.add(order);
                }
                OrderedProduct orderedProduct = new OrderedProduct(
                        order,
                        new Product(
                                resultSet.getString("name"),
                                resultSet.getFloat("default_price")),
                        resultSet.getInt("quantity"));
                order.add(orderedProduct);
            }
        }
        return orders;
    }
}
