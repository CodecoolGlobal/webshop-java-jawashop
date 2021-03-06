package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoJDBC implements ProductDao {

    private DataSource dataSource;

    public ProductDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public ProductDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Product product) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO product (id, name, description, default_price, default_currency, category_id, supplier_id) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, product.getId(), Types.OTHER);
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setFloat(4, product.getDefaultPrice());
            statement.setString(5, product.getDefaultCurrency().getCurrencyCode());
            statement.setObject(6, product.getProductCategory().getId(), Types.OTHER);
            statement.setObject(7, product.getSupplier().getId(), Types.OTHER);
            statement.execute();
        }
    }

    @Override
    public Product find(String id) {
        String query = "SELECT " +
                "       product.id AS product_id, " +
                "       product.name AS product_name, " +
                "       product.description AS product_description, " +
                "       default_price, " +
                "       default_currency, " +
                "       category_id, " +
                "       c.name AS category_name, " +
                "       c.description AS category_description, " +
                "       department, " +
                "       supplier_id, " +
                "       s.name AS supplier_name, " +
                "       s.description AS supplier_description " +
                "FROM product " +
                "JOIN category c on product.category_id = c.id " +
                "JOIN supplier s on product.supplier_id = s.id " +
                "WHERE product.id = ?";

        List<Product> results = executeGetQueries(query, id);
        if (results.size() == 0) {
            return null;
        }
        return results.get(0);
    }

    @Override
    public List<Product> getAll() {
        String query = "SELECT " +
                "       product.id AS product_id, " +
                "       product.name AS product_name, " +
                "       product.description AS product_description, " +
                "       default_price, " +
                "       default_currency, " +
                "       category_id, " +
                "       c.name AS category_name, " +
                "       c.description AS category_description, " +
                "       department, " +
                "       supplier_id, " +
                "       s.name AS supplier_name, " +
                "       s.description AS supplier_description " +
                "FROM product " +
                "JOIN category c on product.category_id = c.id " +
                "JOIN supplier s on product.supplier_id = s.id;";
        return executeGetQueries(query, "");
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        String query = "SELECT " +
                "       product.id AS product_id, " +
                "       product.name AS product_name, " +
                "       product.description AS product_description, " +
                "       default_price, " +
                "       default_currency, " +
                "       category_id, " +
                "       c.name AS category_name, " +
                "       c.description AS category_description, " +
                "       department, " +
                "       supplier_id, " +
                "       s.name AS supplier_name, " +
                "       s.description AS supplier_description " +
                "FROM product " +
                "JOIN category c on product.category_id = c.id " +
                "JOIN supplier s on product.supplier_id = s.id " +
                "WHERE supplier_id = ?";
        return executeGetQueries(query, supplier.getId());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        String query = "SELECT " +
                "       product.id AS product_id, " +
                "       product.name AS product_name, " +
                "       product.description AS product_description, " +
                "       default_price, " +
                "       default_currency, " +
                "       category_id, " +
                "       c.name AS category_name, " +
                "       c.description AS category_description, " +
                "       department, " +
                "       supplier_id, " +
                "       s.name AS supplier_name, " +
                "       s.description AS supplier_description " +
                "FROM product " +
                "JOIN category c on product.category_id = c.id " +
                "JOIN supplier s on product.supplier_id = s.id " +
                "WHERE category_id = ?";
        return executeGetQueries(query, productCategory.getId());
    }

    private List<Product> executeGetQueries(String query, String id){
        List<Product> products = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            if(!id.equals("")){
                statement.setObject(1, id, Types.OTHER);
            }
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Product product = new Product(resultSet.getString("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getFloat("default_price"),
                        resultSet.getString("default_currency"),
                        resultSet.getString("product_description"),
                        new ProductCategory(    resultSet.getString("category_id"),
                                                resultSet.getString("category_name"),
                                                resultSet.getString("department"),
                                                resultSet.getString("category_description")
                        ),
                        new Supplier(   resultSet.getString("supplier_id"),
                                        resultSet.getString("supplier_name"),
                                        resultSet.getString("supplier_description"))
                        );
                products.add(product);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }
}
