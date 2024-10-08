package com.movie.movie_booking.viewmodel.theatrelistscreen;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.dto.Theatre;
import com.movie.movie_booking.viewmodel.BaseScreen;


import java.util.List;
import java.util.Scanner;

public class TheatreListView extends BaseScreen {
    private TheatreListViewModel theatreListViewModel;
    private Scanner scanner;

    public TheatreListView() {
        theatreListViewModel = new TheatreListViewModel(this);
        this.scanner = new Scanner(System.in);
    }

    public void onCreate() {
        if (hasCheckNetworkConnection()) {
            displayAllTheatres();
        } else {
            System.out.println("Check your Internet Connection...........");
        }
    }

    private void displayAllTheatres() {
        List<String> cities = TheatreRepository.getInstance().getCities();
        if (cities.size() != 0)
            displayCities(cities);
        if (cities.size() == 0) {
            System.out.println("Theatre's are currently not available");
        } else {
            System.out.println("Enter city Name");
            String cityName = scanner.nextLine().toLowerCase();
            if (!cities.contains(cityName)) {
                System.out.println("Unavailable city");
                return;
            }
            List<Theatre> theatres = TheatreRepository.getInstance().getCityTheatreMap().get(cityName);
            if (theatres.size() == 0) {
                System.out.println("No theatres available");
            } else {
                for (Theatre theatre : theatres)
                    displayAllTheatres(theatre);
            }
        }
    }

    private void displayAllTheatres(Theatre theatre) {
        System.out.println("--------------------------------");
        System.out.println("City : "+theatre.getCity());
        System.out.println("Theatre Name : " +theatre.getName());
        System.out.println("Total Screens : "+theatre.getTotalScreens());
        System.out.println("--------------------------------");
    }
    private void displayAllTheatres(String name) {
        List<Theatre> theatres = TheatreRepository.getInstance().getCityTheatreMap().get(name);
        for (int i = 0; i<theatres.size(); i++) {
            System.out.println((i+1) + ". " + theatres.get(i).getName());
        }
    }
    private void displayCities(List<String> cities) {
        for (int i = 0; i<cities.size(); i++) {
            System.out.println((i+1) +". " +cities.get(i));
        }
        System.out.println();
    }

}