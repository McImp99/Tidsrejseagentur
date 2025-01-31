package com.example.tidsrejseagentur.backend.domain.bookings.models;

public record BookingCreate(int customerId, int timeMachineId, int timePeriodId, int guideId) {}