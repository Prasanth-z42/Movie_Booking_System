package com.movie.movie_booking.validate;

import java.util.regex.Pattern;

public class Validator {
    public boolean validateCity(String city) {
        return city.matches("[a-zA-Z]+");
    }


    public boolean validatePhoneNumber(String phoneNumber) {
        String regex = "\\d{10}";

        return Pattern.matches(regex, phoneNumber);
    }

    public boolean validateName(String name){
        return name.matches("[a-zA-Z]+") && name.length() >= 3;
    }
}