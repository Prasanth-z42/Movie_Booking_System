package com.movie.movie_booking.viewmodel.movielist;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.dto.Movie;
import com.movie.movie_booking.dto.Screen;
import com.movie.movie_booking.dto.Show;
import com.movie.movie_booking.dto.Theatre;
import com.movie.movie_booking.viewmodel.BaseScreen;
import com.movie.movie_booking.viewmodel.bookingscreen.BookingScreen;

import java.util.List;
import java.util.Scanner;

public class MovieListView extends BaseScreen {
    private BookingScreen bookingScreen;

    private MovieListViewModel movieListViewModel;
    private Scanner scanner;

    public MovieListView() {
        movieListViewModel = new MovieListViewModel(this);
        this.scanner = new Scanner(System.in);
        this.bookingScreen = new BookingScreen();
    }

    public void onCreate() {
        if (hasCheckNetworkConnection()) {
            displayMovies();
        } else {
            System.out.println("Check your Internet Connection...........");
        }
    }

    private void displayMovies() {
        List<String> cities = TheatreRepository.getInstance().getCities();
        if (cities.size() == 0) {
            System.out.println("No available cities");
            return;
        }
        displayCities(cities);
        System.out.println("Enter city Name");
        String cityName = scanner.nextLine().toLowerCase();
        if (!TheatreRepository.getInstance().getCityTheatreMap().containsKey(cityName)) {
            System.out.println("Wrong City Name...!");
            return;
        }
        List<Theatre> theatres = TheatreRepository.getInstance().getCityTheatreMap().get(cityName);
        displayTheatres(theatres);
        System.out.println("Enter Theatre Number");
        int theatreNumber = scanner.nextInt()-1;
        scanner.nextLine();

        Theatre theatre = theatres.get(theatreNumber);
        List<Screen> screenList = theatre.getScreenList();
        displayScreenLists(screenList);
        System.out.println("Enter Screen Number");
        int screenNumber = scanner.nextInt()-1;
        scanner.nextLine();

        Screen screen = screenList.get(screenNumber);
        List<Show> shows = screen.getShows();
        displayShows(shows);

        String choice = "1";

        while (!choice.equals("0")) {
            System.out.println("-------------------------");
            System.out.println("1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("0. Logging out");
            System.out.println("-------------------------");

            System.out.println("Enter your choice");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    bookingScreen.bookTicket(shows, screen);
                    break;
                case "2":
                    bookingScreen.cancelTicket(shows, screen);
                    break;
                case "0":
                    System.out.println("Logging Out..!");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void displayShows(List<Show> shows) {
        for (Show show : shows) {
            displayShow(show);
        }
    }

    private void displayShow(Show show) {
        System.out.println("-------------------------------");
        Movie movie = show.getMovie();
        System.out.println("Movie Name : " + movie.getTitle());
        System.out.println("Duration : " + movie.getDuration());
        System.out.println("Timing : " + show.getTime());
        System.out.println("Available Seats : " + show.getAvailableSeats());
        System.out.println("-------------------------------");
    }

    private void displayScreenLists(List<Screen> screenList) {
        for (int i = 0; i<screenList.size(); i++) {
            System.out.println("Screen " + (i+1) );
        }
        System.out.println();
    }

    private void displayTheatres(List<Theatre> theatres) {
        for (int i = 0; i<theatres.size(); i++) {
            System.out.println((i+1) + ". " + theatres.get(i).getName());
        }
        System.out.println();
    }

    private void displayCities(List<String> cities) {
        for (int i = 0; i<cities.size(); i++) {
            System.out.println((i+1) + ". " + cities.get(i));
        }
    }


}