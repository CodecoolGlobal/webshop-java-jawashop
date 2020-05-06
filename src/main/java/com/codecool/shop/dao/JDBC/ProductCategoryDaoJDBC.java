package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {

    private DataSource dataSource;

    public ProductCategoryDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public ProductCategoryDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) throws SQLException {
        String query = "INSERT INTO category (id, name, description, department) VALUES (?, ?, ?, ?);";
        try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, category.getId(), Types.OTHER);
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setString(4, category.getDepartment());
            statement.execute();
        }
    }

    @Override
    public ProductCategory find(String id) {
        String query = "SELECT * FROM category WHERE id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1,id, Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return new ProductCategory(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> productCategories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try(Connection connection =dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                productCategories.add(new ProductCategory(
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return productCategories;
    }
}
