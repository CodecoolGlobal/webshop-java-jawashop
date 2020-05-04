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
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(String id) {
        ProductCategory tempProductCategory = null;
//        String query = "SELECT * FROM category WHERE id = '" + id + "'";
        String query = "SELECT * FROM category WHERE id = ?;";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1,id, Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                tempProductCategory = new ProductCategory(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tempProductCategory;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        ProductCategory tempProductCategory = null;
        List<ProductCategory> productCategories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try(Connection connection =dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                tempProductCategory = new ProductCategory(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("department"),
                        resultSet.getString("description"));
                productCategories.add(tempProductCategory);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return productCategories;
    }
}
