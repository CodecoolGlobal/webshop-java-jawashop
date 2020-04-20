package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.CreditCardDao;
import com.codecool.shop.model.CreditCard;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class CreditCardDaoJDBC implements CreditCardDao {

    private DataSource dataSource;

    public CreditCardDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public CreditCardDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(CreditCard creditCard) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO credit_cards (id, number, owner_name, expire_date, code) VALUES (?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, creditCard.getId(), Types.OTHER);
            statement.setLong(2, creditCard.getNumber());
            statement.setString(3, creditCard.getOwnerName());
            statement.setString(4, creditCard.getExpireDate());
            statement.setInt(5, creditCard.getCode());
            statement.execute();
        }
    }
}
