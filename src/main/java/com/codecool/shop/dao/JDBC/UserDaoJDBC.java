package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

public class UserDaoJDBC implements UserDao {

    private DataSource dataSource;

    public UserDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public UserDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO users (id, name, email, password) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, user.getId(), Types.OTHER);
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.execute();
        }
    }

    @Override
    public boolean isExists(String email) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "SELECT FROM users WHERE email = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public boolean isAuthTokenExists(String value) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "SELECT FROM users WHERE auth_token = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    @Override
    public Optional<User> find(String email, String password) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "SELECT id, auth_token FROM users WHERE email = ? AND password = ? LIMIT 1";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(new User(
                    resultSet.getString("id"),
                    resultSet.getString("auth_token")));
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "UPDATE users SET auth_token = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getAuthToken());
            statement.setObject(2, user.getId(), Types.OTHER);
            statement.execute();
        }
    }
}
