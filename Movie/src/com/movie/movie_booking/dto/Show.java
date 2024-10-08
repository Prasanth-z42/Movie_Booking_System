package com.movie.movie_booking.dto;

import java.util.ArrayList;
import java.util.List;

public class Show {
    private Movie movie;
    private String time;  // Example: "18:30"
    private int availableSeats;
    public Show(Movie movie, String time, int availableSeats) {
        this.movie = movie;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getTime() {
        return time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public boolean bookSeats(int seats) {
        if (availableSeats >= seats) {
            availableSeats -= seats;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return movie + " at " + time + " (" + availableSeats + " seats available)";
    }

    public void setAvailableSeats(int i) {
        availableSeats = i;
    }
}

