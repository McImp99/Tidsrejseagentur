package com.example.tidsrejseagentur.backend.domain.bookings;

import com.example.tidsrejseagentur.backend.db.Database;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingCreate;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingDelete;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingRead;
import com.example.tidsrejseagentur.backend.domain.bookings.models.BookingUpdate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingAccess implements IBookingAccess {
    private final Connection conn;

    public BookingAccess(Connection conn) {
        this.conn = Database.getInstance().conn;
    }

    @Override
    public List<BookingRead> readAll() throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM bookings");
        var results = stmt.executeQuery();

        List<BookingRead> mapped = new ArrayList<>();

        while (results.next()) {
            var read = new BookingRead(
                    results.getInt("id"),
                    results.getInt("customer_id"),
                    results.getInt("time_machine_id"),
                    results.getInt("time_period_id"),
                    results.getInt("guide_id")
            );

            mapped.add(read);
        }

        results.close();
        stmt.close();

        return mapped;
    }

    @Override
    public BookingRead read(int id) throws SQLException {
        var stmt = conn.prepareStatement("SELECT * FROM bookings WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        var results = stmt.executeQuery();

        BookingRead read = null;
        if (results.next()) {
            read = new BookingRead(
                    results.getInt("id"),
                    results.getInt("customer_id"),
                    results.getInt("time_machine_id"),
                    results.getInt("time_period_id"),
                    results.getInt("guide_id")
            );
        }

        results.close();
        stmt.close();

        return read;
    }

    @Override
    public int add(BookingCreate booking) throws SQLException {
        var stmt = conn.prepareStatement(
                "INSERT INTO bookings (customer_id, time_machine_id, time_period_id, guide_id) VALUES (?, ?, ?, ?)",
                java.sql.Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, booking.customerId());
        stmt.setInt(2, booking.timeMachineId());
        stmt.setInt(3, booking.timePeriodId());
        stmt.setInt(4, booking.guideId());

        stmt.executeUpdate(); // Execute the insert statement

        var generatedKeys = stmt.getGeneratedKeys(); // Retrieve the generated key
        int id = -1;
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }

        stmt.close();
        return id;
    }

    @Override
    public int update(BookingUpdate booking) {
        return 0;
    }

    @Override
    public int delete(BookingDelete booking) throws SQLException {
        var stmt = conn.prepareStatement("DELETE FROM bookings WHERE id = ?");
        stmt.setInt(1, booking.id());
        return stmt.executeUpdate();
    }
}