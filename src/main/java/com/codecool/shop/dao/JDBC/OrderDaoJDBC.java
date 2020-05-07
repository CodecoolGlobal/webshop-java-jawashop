package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.exception.InternalServerException;
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
            String query = "INSERT INTO orders (id, user_id, status_id, name, email, phone_number, billing_address_id, shipping_address_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, order.getId(), Types.OTHER);
            statement.setObject(2, order.getUser().getId(), Types.OTHER);
            statement.setObject(3, order.getStatus().getId(), Types.OTHER);
            statement.setString(4, order.getName());
            statement.setString(5, order.getEmail());
            statement.setLong(6, order.getPhoneNumber());
            statement.setObject(7, order.getBillingAddress().getId(), Types.OTHER);
            statement.setObject(8, order.getShippingAddress().getId(), Types.OTHER);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExists(String id) throws InternalServerException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM orders WHERE id = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, id, Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public void updateStatus(Order order) throws InternalServerException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE orders SET status_id = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, order.getStatus().getId(), Types.OTHER);
            statement.setObject(2, order.getId(), Types.OTHER);
            statement.execute();
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public boolean has(User user, Order order) throws InternalServerException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT id FROM orders WHERE user_id = ? AND id = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, user.getId(), Types.OTHER);
            statement.setObject(2, order.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
    }

    @Override
    public List<Order> getAllWithProducts(User user) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query =
                "SELECT " +
                    "_order.id AS order_id," +
                    "os.name AS status," +
                    "product.name," +
                    "product.default_price," +
                    "op.quantity," +
                    "_order.inserted_at " +
                "FROM orders _order " +
                "LEFT JOIN order_products op on _order.id = op.order_id " +
                "LEFT JOIN order_statuses os on _order.status_id = os.id " +
                "LEFT JOIN product product on op.product_id = product.id " +
                "WHERE _order.user_id = ? " +
                "ORDER BY _order.inserted_at DESC;";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, user.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            Order order = null;
            while (resultSet.next()) {
                if (order == null || !resultSet.getString("order_id").equals(order.getId())) {
                    order = new Order(
                            resultSet.getString("order_id"),
                            new OrderStatus(
                                    null,
                                    resultSet.getString("status")),
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
