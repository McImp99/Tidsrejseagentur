package com.example.tidsrejseagentur.backend.domain.customers;

import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerCreate;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerDelete;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerUpdate;

import java.sql.Connection;
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

        var stmt = conn.prepareStatement("INSERT INTO customers (name, email) VALUES (?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, customer.name());
        stmt.setString(2, customer.email());


        stmt.executeUpdate();

        var generatedKeys = stmt.getGeneratedKeys();
        int id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }

        stmt.close();
        return id;
    }



    @Override
    public int update(CustomerUpdate customer) {
        return 0;
    }

    @Override
    public int delete(CustomerDelete customer) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM customers WHERE id = ?");
        stmt.setInt(1, customer.id());
        return stmt.executeUpdate();
    }
}