package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

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
            String query = "INSERT INTO orders (id, name, email, phone_number, billing_address_id, shipping_address_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, order.getId(), Types.OTHER);
            statement.setString(2, order.getName());
            statement.setString(3, order.getEmail());
            statement.setLong(4, order.getPhoneNumber());
            statement.setObject(5, order.getBillingAddress().getId(), Types.OTHER);
            statement.setObject(6, order.getShippingAddress().getId(), Types.OTHER);
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
}
