package com.example.tidsrejseagentur.backend.domain.customers;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccess implements ICustomerAccess {
    Connection conn;

    public CustomerAccess(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<CustomerRead> readAll() throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM customers");
        var results = stmt.executeQuery();

        List<CustomerRead> mapped = new ArrayList<>();

        while (results.next()) {
            var read = new CustomerRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("email")
            );

            mapped.add(read);
        }

        results.close();
        stmt.close();

        return mapped;
    }

    @Override
    public CustomerRead read(int id) throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM customers WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        var results = stmt.executeQuery();

        CustomerRead read = null;
        if (results.next()) {
            read = new CustomerRead(
                    results.getInt("id"),
                    results.getString("name"),
                    results.getString("email")
            );
        }

        results.close();
        stmt.close();

        return read;
    }

    @Override

    public int add(CustomerCreate customer) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM customers WHERE email = ?";
        String insertQuery = "INSERT INTO customers (name, email) VALUES (?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery, java.sql.Statement.RETURN_GENERATED_KEYS)) {


            checkStmt.setString(1, customer.email());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: Email already exists in the database.");
                return -1;
            }


            insertStmt.setString(1, customer.name());
            insertStmt.setString(2, customer.email());
            insertStmt.executeUpdate();


            ResultSet generatedKeys = insertStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
            return -1;

        }
    }

    @Override
    public int update(CustomerUpdate customer) throws SQLException {
        var stmt = conn.prepareStatement("UPDATE customers SET name = ?, email = ? WHERE id = ?");
        stmt.setString(1, customer.name());
        stmt.setString(2, customer.email());
        stmt.setInt(3, customer.id());

        int rowsAffected = stmt.executeUpdate();
        stmt.close();

        return rowsAffected;
    }




    @Override
    public int delete(CustomerDelete customer) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM customers WHERE id = ?");
        stmt.setInt(1, customer.id());
        return stmt.executeUpdate();
    }
}