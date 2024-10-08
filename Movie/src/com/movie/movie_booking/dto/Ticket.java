package com.movie.movie_booking.dto;

public class Ticket {
    private String customerName;
    private String customerNumber;
    private String seatNumber;
    public Ticket(String customerName, String customerNumber) {
        this.customerName = customerName;
        this.customerNumber = customerNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }
}
