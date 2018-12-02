package com.globalLogic.carRide.dto;

public enum BookingStatus {

    CANCELLED("cancelled"), BOOKED("booked"), PENDINGFORCONFIRMATION("PendingForConfiramtion");

    String status;

    BookingStatus(String status) {
        this.status = status;
    }
}
