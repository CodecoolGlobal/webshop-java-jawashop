package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.OrderedProductDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OrderedProduct> findBy(Order order) throws SQLException {
        List<OrderedProduct> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();) {
            String query =
                    "SELECT " +
                        "product.id, product.name, product.description, product.default_price AS price, product.default_currency AS currency, " +
                        "order_product.quantity " +
                    "FROM order_products AS order_product " +
                    "LEFT JOIN product ON product.id = order_product.product_id " +
                    "WHERE order_product.order_id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, order.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                products.add(new OrderedProduct(
                        null,
                        new Product(
                                resultSet.getString("id"),
                                resultSet.getString("name"),
                                resultSet.getFloat("price"),
                                resultSet.getString("currency"),
                                resultSet.getString("description"),
                                new ProductCategory(null, null, null, null),
                                new Supplier(null, null, null)),
                        resultSet.getInt("quantity")));
            }
        }

        return products;
    }
}
