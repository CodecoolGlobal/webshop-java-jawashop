package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {
    private DataSource dataSource;

    public SupplierDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(String id) {
        Supplier tempSupplier = null;
        String query = "SELECT * FROM supplier WHERE id = '" + id + "'";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        ){
            if(resultSet.next()){
                tempSupplier = new Supplier(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));


            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tempSupplier;
    }

    @Override
    public void remove(String id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
