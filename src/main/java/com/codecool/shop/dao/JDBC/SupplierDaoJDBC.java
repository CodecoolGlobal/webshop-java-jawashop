package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

    private DataSource dataSource;

    public SupplierDaoJDBC() {
        this(DbConnection.getConnection());
    }
    public SupplierDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO supplier (id, name, description) " +
                "VALUES (?,?,?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1,supplier.getId(), Types.OTHER);
            statement.setString(2,supplier.getName());
            statement.setString(3,supplier.getDescription());
            statement.executeQuery();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(String id) {
        Supplier tempSupplier = null;
        String query = "SELECT * FROM supplier WHERE id = ?";
        try(Connection connection = dataSource.getConnection();){
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1,id,Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
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
        Supplier tempSupplier = null;
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                tempSupplier = new Supplier(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                suppliers.add(tempSupplier);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return suppliers;
    }
}
