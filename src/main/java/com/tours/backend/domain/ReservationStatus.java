package com.tours.backend.domain;

public enum ReservationStatus {
    CONFIRMED("CONFIRMED"),
    PENDING("PENDING"),
    CANCELLED("CANCELLED");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ReservationStatus fromString(String status) {
        for (ReservationStatus reservationStatus : ReservationStatus.values()) {
            if (reservationStatus.status.equalsIgnoreCase(status)) {
                return reservationStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
