package com.codecool.shop.dao.JDBC;

import com.codecool.shop.config.DbConnection;
import com.codecool.shop.dao.PaymentDao;
import com.codecool.shop.model.Payment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PaymentDaoJDBC implements PaymentDao {

    private DataSource dataSource;

    public PaymentDaoJDBC() {
        this(DbConnection.getConnection());
    }

    public PaymentDaoJDBC(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void add(Payment payment) throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            String query = "INSERT INTO order_payments (id, order_id, credit_card_id) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setObject(1, payment.getId(), Types.OTHER);
            statement.setObject(2, payment.getOrder().getId(), Types.OTHER);
            statement.setObject(3, payment.getCreditCard().getId(), Types.OTHER);
            statement.execute();
        }
    }
}
