package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.Address;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class AddressDaoJDBC implements AddressDao {

    private DataSource dataSource;

    public AddressDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public AddressDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Address address) {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO addresses (id, country, city, zip_code, address) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, address.getId(), Types.OTHER);
            statement.setString(2, address.getCountry());
            statement.setString(3, address.getCity());
            statement.setString(4, address.getZipCode());
            statement.setString(5, address.getAddress());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
