package com.example.tidsrejseagentur.backend.domain.bookings.models;

public record BookingRead(int id, int customerId, int timeMachineId, int timePeriodId, int guideId) {}
