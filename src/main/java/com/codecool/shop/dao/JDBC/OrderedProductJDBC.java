package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.model.OrderedProduct;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class OrderedProductJDBC implements OrderedProductDao {

    private DataSource dataSource;

    public OrderedProductJDBC() {
        this(DbConnection.getConnection());
    }

    public OrderedProductJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(OrderedProduct orderedProduct) {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO order_products (id, order_id, product_id, quantity) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, orderedProduct.getId(), Types.OTHER);
            statement.setObject(2, orderedProduct.getOrder().getId(), Types.OTHER);
            statement.setObject(3, orderedProduct.getProduct().getId(), Types.OTHER);
            statement.setInt(4, orderedProduct.getQuantity());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
