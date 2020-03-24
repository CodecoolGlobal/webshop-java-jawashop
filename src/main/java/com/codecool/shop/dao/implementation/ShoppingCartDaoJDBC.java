package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "INSERT INTO cart (id, product_id, quantity) VALUES ('"+ uuid +"', '" + product.getId() + "', '1')";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
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
        String query = "SELECT * FROM cart";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                products.add(new ProductDaoJDBC().find(resultSet.getString("product_id")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
