package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.exception.InternalServerException;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.OrderStatus;

import javax.sql.DataSource;
import java.sql.*;
import java.util.UUID;

public class OrderStatusDaoJDBC {

    private DataSource dataSource;

    public OrderStatusDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public OrderStatusDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public OrderStatus get(OrderStatus orderStatus) throws InternalServerException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "SELECT id FROM order_statuses WHERE name = ? LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, orderStatus.getName());
            ResultSet resultset = statement.executeQuery();
            if (resultset.next()) {
                return new OrderStatus(
                        UUID.fromString(resultset.getString("id")),
                        orderStatus.getName());
            }
        } catch (SQLException e) {
            throw new InternalServerException(e);
        }
        return null;
    }
}
