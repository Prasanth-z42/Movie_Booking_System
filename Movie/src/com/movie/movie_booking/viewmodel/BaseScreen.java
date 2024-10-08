package com.movie.movie_booking.viewmodel;

public class BaseScreen {
    public boolean hasCheckNetworkConnection() {
        return checkNetworkConnection();
    }

    public boolean checkNetworkConnection() {
        return true;
    }
}
