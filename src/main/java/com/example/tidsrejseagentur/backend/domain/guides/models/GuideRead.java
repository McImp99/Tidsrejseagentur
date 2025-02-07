package com.example.tidsrejseagentur.backend.domain.guides.models;

public record GuideRead(int id, String name, String speciality) {
    @Override
    public String toString() {
        return name + " - " + speciality;
    }
}