package com.movie.movie_booking.validate;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.datalayer.UserRepository;
import com.movie.movie_booking.dto.Admin;
import com.movie.movie_booking.dto.User;

public class Authenticator {

    public boolean authenticateCity(String city) {
        return TheatreRepository.getInstance().getCities().contains(city);
    }

    public User authenticateAndGetUser(String phoneNumber, String password) {
        UserRepository userRepository = UserRepository.getInstance();

        if (userRepository.authenticateUser(phoneNumber, password)) {
            return userRepository.getUserByPhoneNumber(phoneNumber);
        }
        return null;
    }

    public Admin authenticateAndGetAdmin(String name, String password) {
        return new Admin(name, password);
    }
}
