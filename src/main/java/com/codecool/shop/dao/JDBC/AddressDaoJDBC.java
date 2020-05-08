package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoJDBC implements AddressDao {

    private DataSource dataSource;

    public AddressDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public AddressDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Address address) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO addresses (id, user_id, country, city, zip_code, address) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, address.getId(), Types.OTHER);
            statement.setObject(2, address.getUser().getId(), Types.OTHER);
            statement.setString(3, address.getCountry());
            statement.setString(4, address.getCity());
            statement.setString(5, address.getZipCode());
            statement.setString(6, address.getAddress());
            statement.execute();
        }
    }

    @Override
    public List<Address> getAllBy(User user) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        String query = "SELECT * FROM addresses WHERE user_id = ?;";
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, user.getId(), Types.OTHER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address(
                        resultSet.getString("id"),
                        null,
                        resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("zip_code"),
                        resultSet.getString("address"));
                addresses.add(address);
            }
        }
        return addresses;
    }
}
