package com.acme.takemycar.rent.domain.model.valueobjects;

public record Plate(String value) {
    public Plate {
        if (value == null || !value.matches("[A-Z]{3}-\\d{3}")) {
            throw new IllegalArgumentException("Invalid license plate format. It should be three uppercase letters followed by a dash and three digits (e.g., ABC-123).");
        }
    }
}
