package com.movie.movie_booking.dto;

import java.util.List;

public class Movie {
    private String title;
    private String duration;  // in minutes

    public Movie(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return title + " (" + duration + " mins)";
    }
}
