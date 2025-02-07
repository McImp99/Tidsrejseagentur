package com.example.tidsrejseagentur.backend.domain.time_periods.models;

public record TimePeriodRead(int id, String name, String description) {
    @Override
    public String toString() {
        return name + " - " + description; // Combine name and description for ComboBox display
    }
}