package com.example.tidsrejseagentur.backend.db;

import com.example.tidsrejseagentur.backend.domain.bookings.BookingAccess;
import com.example.tidsrejseagentur.backend.domain.bookings.IBookingAccess;
import com.example.tidsrejseagentur.backend.domain.customers.CustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.ICustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.models.CustomerRead;
import com.example.tidsrejseagentur.backend.domain.guides.GuideAccess;
import com.example.tidsrejseagentur.backend.domain.guides.IGuideAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.ITimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.TimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.ITimePeriodAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.TimePeriodAccess;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final Database instance;

    static {
        try {
            instance = new Database("jdbc:mysql://localhost:3306/timetravel", "admin", "admin");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final Connection conn;

    public IBookingAccess bookings;
    public ICustomerAccess customers;
    public IGuideAccess guides;
    public ITimeMachineAccess timeMachines;
    public ITimePeriodAccess timePeriods;

    Database(String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Flyway flyway = Flyway.configure().dataSource(url, username, password).load();
        flyway.migrate();

        conn = DriverManager.getConnection(url, username, password);

        bookings = new BookingAccess(conn);
        customers = new CustomerAccess(conn);
        guides = new GuideAccess(conn);
        timeMachines = new TimeMachineAccess(conn);
        timePeriods = new TimePeriodAccess(conn);
    }

    public static Database getInstance() {
        return instance;
    }
}