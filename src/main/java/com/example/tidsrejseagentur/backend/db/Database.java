package com.example.tidsrejseagentur.backend.db;

import com.example.tidsrejseagentur.backend.domain.bookings.IBookingAccess;
import com.example.tidsrejseagentur.backend.domain.customers.ICustomerAccess;
import com.example.tidsrejseagentur.backend.domain.guides.IGuideAccess;
import com.example.tidsrejseagentur.backend.domain.time_machines.ITimeMachineAccess;
import com.example.tidsrejseagentur.backend.domain.time_periods.ITimePeriodAccess;

import java.sql.Connection;

public class Database {
    private static Database instance = new Database();

    private Connection conn;

    private IBookingAccess bookings;
    private ICustomerAccess customers;
    private IGuideAccess guides;
    private ITimeMachineAccess timeMachines;
    private ITimePeriodAccess timePeriods;

    public static Database getInstance() {
        return instance;
    }
}