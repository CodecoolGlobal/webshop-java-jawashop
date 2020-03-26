package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCartDaoJDBC implements ShoppingCartDao {
    private DataSource dataSource;

    public ShoppingCartDaoJDBC(){ this(DbConnection.getConnection()); }
    public ShoppingCartDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) {
        CartItem foundCartItem = find(product);

        if (foundCartItem == null) {
            UUID uuid = UUID.randomUUID();
            String query = "INSERT INTO cart (id, product_id, quantity) VALUES (?,?,?)";
            try (Connection connection = dataSource.getConnection();) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setObject(1, uuid, Types.OTHER);
                statement.setObject(2, product.getId(), Types.OTHER);
                statement.setInt(3, 1);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String query = "UPDATE cart SET quantity = quantity + 1 WHERE product_id = ?;";
            try (Connection connection = dataSource.getConnection();) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setObject(1, product.getId(), Types.OTHER);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
         }
    }

    @Override
    public CartItem find(Product product) {
        String query = "SELECT * FROM cart WHERE product_id = ?;";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, product.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new CartItem(
                        resultSet.getString("id"),
                        new Product(
                                resultSet.getString("product_id"),
                                null,
                                0,
                                "JPY",
                                null,
                                new ProductCategory(null, null, null, null),
                                new Supplier(null, null, null)),
                        resultSet.getInt("quantity"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void remove(CartItem cartItem) {
        String query = "DELETE FROM cart WHERE id = ?;";
        try (Connection connection = dataSource.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, cartItem.getId(), Types.OTHER);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CartItem cartItem) {
        String query = "UPDATE cart SET quantity = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cartItem.getQuantity());
            statement.setObject(2, cartItem.getId(), Types.OTHER);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CartItem> getAll() {
        List<CartItem> products = new ArrayList<>();
        String query = "SELECT cart.id, " +
                "       product_id, " +
                "       quantity, " +
                "       p.name AS product_name, " +
                "       p.description AS product_description, " +
                "       default_price, " +
                "       default_currency, " +
                "       c.id AS category_id, " +
                "       c.name AS category_name, " +
                "       c.description AS category_description, " +
                "       department, " +
                "       s.id AS supplier_id, " +
                "       s.name AS supplier_name, " +
                "       s.description AS supplier_description " +
                "FROM cart " +
                "JOIN product p on cart.product_id = p.id " +
                "JOIN category c on p.category_id = c.id " +
                "JOIN supplier s on p.supplier_id = s.id;";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(
                        new CartItem(
                                new Product(
                                        resultSet.getString("product_id"),
                                        resultSet.getString("product_name"),
                                        resultSet.getFloat("default_price"),
                                        resultSet.getString("default_currency"),
                                        resultSet.getString("product_description"),
                                        new ProductCategory(
                                                resultSet.getString("category_id"),
                                                resultSet.getString("category_name"),
                                                resultSet.getString("department"),
                                                resultSet.getString("category_description")),
                                        new Supplier(
                                                resultSet.getString("supplier_id"),
                                                resultSet.getString("supplier_name"),
                                                resultSet.getString("supplier_description"))),
                                resultSet.getInt("quantity")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
