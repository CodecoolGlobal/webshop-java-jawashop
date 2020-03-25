package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ShoppingCartDao;
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
        UUID uuid = UUID.randomUUID();
        String query = "INSERT INTO cart (id, product_id, quantity) VALUES (?,?,?)";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, uuid, Types.OTHER);
            statement.setObject(2,product.getId(), Types.OTHER);
            statement.setInt(3,1);
            statement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Product find(String id) {
        return null;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
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
            while(resultSet.next()){
                products.add(new Product(
                   resultSet.getString("product_id"),
                   resultSet.getString("product_name"),
                   resultSet.getFloat("default_price"),
                   resultSet.getString("default_currency"),
                   resultSet.getString("product_description"),
                   new ProductCategory( resultSet.getString("category_id"),
                                        resultSet.getString("category_name"),
                                        resultSet.getString("department"),
                                        resultSet.getString("category_description")
                                        ),
                   new Supplier(        resultSet.getString("supplier_id"),
                                        resultSet.getString("supplier_name"),
                                        resultSet.getString("supplier_description"))
                ));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
