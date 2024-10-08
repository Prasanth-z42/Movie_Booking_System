package com.movie.movie_booking.dto;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int idProvider;
    private int id;
    private String name;
    private String phoneNumber;
    private String password;
    List<Booking> bookedTickets;

    public User(String name, String phoneNumber, String password) {
        this.id = ++idProvider;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.bookedTickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookedTickets() {
        return bookedTickets;
    }

    public void addBooking(Booking booking) {
        this.bookedTickets.add(booking);
    }

}