package com.example.tidsrejseagentur.backend.db;

import com.example.tidsrejseagentur.backend.domain.bookings.IBookingAccess;
import com.example.tidsrejseagentur.backend.domain.customers.ICustomerAccess;
import com.example.tidsrejseagentur.backend.domain.guides.IGuideAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.ITimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.ITimePeriodAccess;

import java.sql.Connection;

public class Database {
    private static final Database instance = new Database();

    private Connection conn;

    public IBookingAccess bookings;
    public ICustomerAccess customers;
    public IGuideAccess guides;
    public ITimeMachineAccess timeMachines;
    public ITimePeriodAccess timePeriods;

    public static Database getInstance() {
        return instance;
    }
}