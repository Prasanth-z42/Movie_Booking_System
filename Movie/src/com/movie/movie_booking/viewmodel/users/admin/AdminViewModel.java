package com.movie.movie_booking.viewmodel.users.admin;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.dto.Movie;
import com.movie.movie_booking.dto.Screen;
import com.movie.movie_booking.dto.Show;
import com.movie.movie_booking.dto.Theatre;
import com.movie.movie_booking.validate.Authenticator;
import com.movie.movie_booking.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AdminViewModel{
    private Validator validator;
    private Authenticator authenticator;
    private AdminView adminView;
    private Scanner scanner;
    public AdminViewModel(AdminView adminView) {
        this.adminView = adminView;
        this.validator = new Validator();
        this.scanner = new Scanner(System.in);
    }


    boolean validateCity(String city) {
        return validator.validateCity(city);
    }

    boolean addTheatre(String city, String theatreName, int totalScreens, List<Screen> screenList) {
        city = city.toLowerCase();
        TheatreRepository.getInstance().addCity(city);
        Theatre theatre = new Theatre(city, theatreName, totalScreens, screenList);
        TheatreRepository.getInstance().getTheatreMap().put(theatreName, theatre);
        if (TheatreRepository.getInstance().getCityTheatreMap().get(city) == null) {
            List<Theatre> theatres = new ArrayList<>();
            theatres.add(theatre);
            TheatreRepository.getInstance().getCityTheatreMap().put(city, theatres);
        }
        else{
            List<Theatre> list = TheatreRepository.getInstance().getCityTheatreMap().get(city);
            list.add(theatre);
        }
        return true;
    }




    public Show addShow(Screen screen) {
        System.out.println("Enter Movie Name");
        String movieName = scanner.nextLine();
        System.out.println("Enter Duration");
        String duration = scanner.nextLine();
        Movie movie = new Movie(movieName, duration);
        System.out.println("Enter Timing");
        String time = scanner.nextLine();
        Show show = new Show(movie, time, screen.getCapacity());
        screen.addShows(show);
        return show;
    }
}
