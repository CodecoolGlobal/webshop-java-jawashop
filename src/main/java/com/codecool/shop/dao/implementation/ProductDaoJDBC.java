package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao {
    private DataSource datasource;

    public ProductDaoJDBC(){
        this(DbConnection.getConnection());
    }

    public ProductDaoJDBC(DataSource dataSource){
        this.datasource = dataSource;
    }


    @Override
    public void add(Product product) {

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
        String query = "SELECT * FROM product";
        return executeGetQueries(query);
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT * FROM product WHERE supplier_id = '"+ supplier.getId() +"'";
        return executeGetQueries(query);
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT * FROM product WHERE category_id = '"+ productCategory.getId() +"'";
        return executeGetQueries(query);
    }


    private List<Product> executeGetQueries(String query){
        Product tempProduct;
        List<Product> products = new ArrayList<>();
        try(Connection connection = datasource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
        ) {
            while(resultSet.next()){
                tempProduct = new Product(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("description"),
                        new ProductCategoryDaoJDBC(datasource).find(resultSet.getString("category_id")),
                        new SupplierDaoJDBC(datasource).find(resultSet.getString("supplier_id")));
                products.add(tempProduct);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
