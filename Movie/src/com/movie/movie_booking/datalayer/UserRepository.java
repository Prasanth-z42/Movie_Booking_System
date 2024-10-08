package com.movie.movie_booking.datalayer;

import com.movie.movie_booking.dto.Admin;
import com.movie.movie_booking.dto.User;

import java.util.HashMap;

public class UserRepository {
    private static UserRepository repository;
    private User currentUser;

    private static HashMap<String, User> userPhoneNumberMap = new HashMap<>();
    private static HashMap<String, String> userPhoneNumberPasswordMap = new HashMap<>();
    private static HashMap<Integer, User> userIdMap = new HashMap<>();
    private static HashMap<String, String> adminPhoneNumberPasswordMap = new HashMap<>();
    private static HashMap<String, Admin> adminPhoneNumberMap = new HashMap<>();


    private UserRepository() {}

    public static UserRepository getInstance() {
        if (repository == null) {
            repository = new UserRepository();
        }
        return repository;
    }

    public Admin authenticateAdmin(String phoneNumber, String password) {
        if(adminPhoneNumberPasswordMap.containsKey(phoneNumber)){
            if(adminPhoneNumberPasswordMap.get(phoneNumber).equals(password)){
                return adminPhoneNumberMap.get(phoneNumber);
            }
            return null;
        }
        return null;
    }

    public boolean addUser(User user) {
        if (!userPhoneNumberMap.containsKey(user.getPhoneNumber())) {
            userPhoneNumberMap.put(user.getPhoneNumber(), user);
            userPhoneNumberPasswordMap.put(user.getPhoneNumber(), user.getPassword());  // Storing phone number and password
        } else {
            return false;
        }

        if (!userIdMap.containsKey(user.getId())) {
            userIdMap.put(user.getId(), user);
            return true;
        } else {
            return false;
        }
    }


    public User getUserById(int id) {
        return userIdMap.getOrDefault(id, null);
    }

    public User getUserByPhoneNumber(String phoneNumber) {
        return userPhoneNumberMap.getOrDefault(phoneNumber, null);
    }

    public boolean authenticateUser(String phoneNumber, String password) {
        String storedPassword = userPhoneNumberPasswordMap.get(phoneNumber);
        if(storedPassword != null && storedPassword.equals(password)){
            currentUser = userPhoneNumberMap.get(phoneNumber);
            return true;
        }
        return false;
    }
    public User getCurrentUser() {
        return currentUser;
    }

}
