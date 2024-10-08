package com.movie.movie_booking.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private String id;
    private User user;
    private Movie movie;
    private String showTiming;
    private String screenName;
    private String theatreName;
    private LocalDateTime bookingDate;
    private int numberOfTickets;
    private int[] seatNumbers;

    public Booking(User user, Movie movie, String showTime, String screenName,String theatreName, LocalDateTime bookingDate, int numberOfTickets) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.movie = movie;
        this.showTiming = showTime;
        this.screenName=screenName;
        this.theatreName = theatreName;
        this.bookingDate = bookingDate;
        this.numberOfTickets = numberOfTickets;
        this.seatNumbers = new int[2];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getShowTiming() {
        return showTiming;
    }

    public void setShowTiming(String showTiming) {
        this.showTiming = showTiming;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }



//    @Override
//    public String toString() {
//        return "Booking{" +
//                "id='" + id + '\'' +
//                ", user=" + user +
//                ", movie=" + movie +
//                ", showTiming='" + showTiming + '\'' +
//                ", screenName='" + screenName + '\'' +
//                ", theatreName='" + theatreName + '\'' +
//                ", bookingDate=" + bookingDate +
//                ", numberOfTickets=" + numberOfTickets +
//                ", seatNumbers=" + Arrays. +
//                '}';
//    }
}
