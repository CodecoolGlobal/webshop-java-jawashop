package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private DataSource dataSource;

    public ProductCategoryDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(ProductCategory category) {

    }

    @Override
    public ProductCategory find(String id) {
        ProductCategory tempProductCategory = null;
        String query = "SELECT * FROM category WHERE id = '" + id + "'";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
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
        return null;
    }
}
