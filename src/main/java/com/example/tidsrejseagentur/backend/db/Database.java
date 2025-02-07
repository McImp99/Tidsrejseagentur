package com.example.tidsrejseagentur.backend.db;

import com.example.tidsrejseagentur.backend.domain.bookings.BookingAccess;
import com.example.tidsrejseagentur.backend.domain.bookings.IBookingAccess;
import com.example.tidsrejseagentur.backend.domain.customers.CustomerAccess;
import com.example.tidsrejseagentur.backend.domain.customers.ICustomerAccess;
import com.example.tidsrejseagentur.backend.domain.guides.GuideAccess;
import com.example.tidsrejseagentur.backend.domain.guides.IGuideAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.ITimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.TimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.ITimePeriodAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.TimePeriodAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Database instance;

    public final Connection conn;


    public IBookingAccess bookings;
    public ICustomerAccess customers;
    public IGuideAccess guides;
    public ITimeMachineAccess timeMachines;
    public ITimePeriodAccess timePeriods;

    private Database() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/timetraveldb";
        String username = "root";
        String password = "pass123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the MySQL driver is present
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database successfully!");

            bookings = new BookingAccess(conn);
            customers = new CustomerAccess(conn);
            guides = new GuideAccess(conn);
            timeMachines = new TimeMachineAccess(conn);
            timePeriods = new TimePeriodAccess(conn);

        } catch (SQLException | ClassNotFoundException e) {
            // Log the error and throw a runtime exception
            System.err.println("Failed to initialize the database connection.");
            e.printStackTrace();
            throw e;  // Rethrow the exception to prevent further use of an invalid connection
        }
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            try {
                instance = new Database();
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException("Error initializing database", e);
            }
        }
        return instance;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
