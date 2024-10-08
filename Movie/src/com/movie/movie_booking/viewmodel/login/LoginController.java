package com.movie.movie_booking.viewmodel.login;

import com.movie.movie_booking.datalayer.UserRepository;
import com.movie.movie_booking.dto.Admin;
import com.movie.movie_booking.dto.User;
import com.movie.movie_booking.validate.Authenticator;
import com.movie.movie_booking.validate.Validator;

public class LoginController {
    private LoginView loginView;
    private Validator validator;
    private Authenticator authenticator;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.validator = new Validator();
        this.authenticator = new Authenticator();
    }

    boolean validateName(String name) {
        return validator.validateName(name);
    }

    Admin authenticateAndGetAdmin(String name, String password) {
        return authenticator.authenticateAndGetAdmin(name, password);
    }

    boolean validatePhoneNumber(String phoneNumber) {
        return validator.validatePhoneNumber(phoneNumber);
    }

    User registerUser(String name, String phoneNumber, String password) {
        if (UserRepository.getInstance().getUserByPhoneNumber(phoneNumber) != null) {
            return UserRepository.getInstance().getUserByPhoneNumber(phoneNumber);
        }
        User user = new User(name, phoneNumber, password);
        UserRepository.getInstance().addUser(user);
        return user;
    }

    // Authenticate user by phone number and password
    User authenticateAndGetUser(String phoneNumber, String password) {
        User user = authenticator.authenticateAndGetUser(phoneNumber, password);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}
