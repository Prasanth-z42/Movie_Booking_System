package com.movie.movie_booking.viewmodel.users.user;

import com.movie.movie_booking.viewmodel.movielist.MovieListView;

import java.util.Scanner;

public class UserView{
    private UserViewModel userViewModel;
    private Scanner scanner;
    public UserView(){
        this.userViewModel = new UserViewModel(this);
        this.scanner = new Scanner(System.in);
    }

    public void displayUserMenu() {
        System.out.println("Login successful!\n");
        String userChoice = "";
        while (!userChoice.equals("0")) {
            System.out.println("1. View Movies");
            System.out.println("0. Logout");
            userChoice = scanner.nextLine();
            switch (userChoice) {
                case "1":
                    MovieListView movieListView = new MovieListView();
                    movieListView.onCreate();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Invalid choice!\n");
            }
        }
    }
}
