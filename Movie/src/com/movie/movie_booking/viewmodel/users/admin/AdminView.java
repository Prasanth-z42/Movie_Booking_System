package com.movie.movie_booking.viewmodel.users.admin;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.dto.Screen;
import com.movie.movie_booking.dto.Show;
import com.movie.movie_booking.dto.Theatre;
import com.movie.movie_booking.dto.Ticket;
import com.movie.movie_booking.viewmodel.theatrelistscreen.TheatreListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView{
    private Scanner scanner;
    private AdminViewModel adminViewModel;
    public AdminView() {
        this.scanner = new Scanner(System.in);
        adminViewModel = new AdminViewModel(this);
    }

    public void displayAdminMenu() {
        System.out.println("Login successful!\n");
        boolean flag = true;
        while (flag) {
            System.out.println("1. Add Theatre ");
            System.out.println("2. Add show ");
            System.out.println("3. View Theatres ");
            System.out.println("4. View audience in the show");
            System.out.println("0. Logout");
            System.out.println("Enter your choice:");
            String adminChoice = scanner.nextLine();
            switch (adminChoice) {
                default:
                    System.out.println("Invalid choice!\n");
                    break;
                case "1":
                    addTheatre();
                    break;
                case "2":
                    addShows();
                    break;
                case "3":
                    TheatreListView theatreListView = new TheatreListView();
                    theatreListView.onCreate();
                    break;
                case "4":
                    viewCustomersInTheShow();
                    break;
                case "0":
                    System.out.println("Logging out...!");
                    flag = false;
                    break;
            }
        }
    }

    private void viewCustomersInTheShow() {
        if (TheatreRepository.getInstance().getCities().size() == 0) {
            System.out.println("No available cities");
            return;
        }
        List<String> cities = TheatreRepository.getInstance().getCities();
        availableCities(cities);
        System.out.println("Enter city Name");
        String cityName = scanner.nextLine().toLowerCase();
        if (TheatreRepository.getInstance().getCityTheatreMap().get(cityName) == null) {
            System.out.println("No Theatres available in this city");
            return;
        }
        List<Theatre> theatres = TheatreRepository.getInstance().getCityTheatreMap().get(cityName);
        int k = 1;
        for (Theatre theatre : theatres) {
            displayAllTheatres(theatre, k++);
        }
        System.out.println("Enter Theatre Name");
        String theatreName = scanner.nextLine().toLowerCase();
        Theatre theatre = TheatreRepository.getInstance().getTheatreMap().get(theatreName);
        List<Screen> screenList = theatre.getScreenList();
        displayScreenLists(screenList);
        System.out.println("Enter Screen Number");
        int number = scanner.nextInt()-1;
        scanner.nextLine();
        Screen screen = screenList.get(number);
        displayShowDetails(screen.getShows(), theatre);
        System.out.println("Enter Show Number");
        int showNumber = scanner.nextInt()-1;
        Show show = null;
        if (screen.getShows().contains(showNumber))
            show = screen.getShows().get(showNumber);
        if (show != null)
            displayTickets(screen.getSeats(), show, screen);
    }
    private void displayShowDetails(List<Show> shows, Theatre theatre) {
        if (shows.size() == 0) System.out.println("Shows are not available in the theatre...!");
        else {
            for (Show show : shows) {
                displayShowDetails(show, theatre);
            }
            System.out.println();
        }
    }
    private void displayTickets(Ticket[][] seats, Show show, Screen screen) {
        boolean flag = false;
        for (int i = 0; i< seats.length; i++) {
            for (int j = 0; j<seats[i].length; j++) {
                if (seats[i][j] != null) {
                    flag = true;
                    displayTicketDetails(seats[i][j], show, screen);
                }
            }
        }
        if (!flag)
            System.out.println("Unavailable customers...");
    }

    private void displayTicketDetails(Ticket ticket, Show show, Screen screen) {
        System.out.println("----------------------------------------");
        System.out.println("Customer Name : "+ticket.getCustomerName());
        System.out.println("Customer Number : "+ticket.getCustomerNumber());
        System.out.println("Seat Number : "+ticket.getSeatNumber());
        System.out.println("Movie Name : " + show.getMovie().getTitle());
        System.out.println("Screen Number : " + screen.getName());
        System.out.println("----------------------------------------");
    }

    private void addShows() {
        if (TheatreRepository.getInstance().getCities().size() == 0) {
            System.out.println("No available cities");
            return;
        }
        List<String> cities = TheatreRepository.getInstance().getCities();
        availableCities(cities);
        System.out.println("Enter city Name");
        String cityName = scanner.nextLine().toLowerCase();
        if (TheatreRepository.getInstance().getCityTheatreMap().get(cityName) == null) {
            System.out.println("No Theatres available in this city");
            return;
        }
        List<Theatre> theatres = TheatreRepository.getInstance().getCityTheatreMap().get(cityName);
        int k = 1;
        for (Theatre theatre : theatres) {
            displayAllTheatres(theatre, k++);
        }
        System.out.println("Enter Theatre Name");
        String theatreName = scanner.nextLine().toLowerCase();
        Theatre theatre = TheatreRepository.getInstance().getTheatreMap().get(theatreName);
        List<Screen> screenList = theatre.getScreenList();
        displayScreenLists(screenList);
        System.out.println("Enter Screen Number");
        int number = scanner.nextInt()-1;
        scanner.nextLine();
        Screen screen = screenList.get(number);
        Show show = adminViewModel.addShow(screen);
        if (show != null) {
            displayShowDetails(show, theatre);
            System.out.println("Show added successfully");
        } else {
            System.out.println("Occurred some problems please check the details");
        }
    }

    private void displayShowDetails(Show show, Theatre theatre) {
        System.out.println("---------------------------");
        System.out.println("Theatre Name : " + theatre.getName());
        System.out.println("City : " + theatre.getCity());
        System.out.println("Movie Name : " + show.getMovie().getTitle());
        System.out.println("Duration : " + show.getMovie().getDuration());
        System.out.println("Timing : " + show.getTime());
        System.out.println("---------------------------");
    }

    private void displayScreenLists(List<Screen> screenList) {
        for (Screen screen : screenList) {
            System.out.println("----------------------------");
            System.out.println(screen.getName());
            System.out.println(screen.getCapacity());
            System.out.println("----------------------------");
        }
    }

    private void displayAllTheatres(Theatre theatre, int k) {
        System.out.println(k + ". "+theatre.getName());
    }


    private void availableCities(List<String> cities) {
        for (int i = 0; i<cities.size(); i++) {
            System.out.println((i+1) + ". " + cities.get(i));
        }
        System.out.println();
    }

    private void addTheatre() {
        System.out.print("Enter city name : ");
        String city = scanner.nextLine();
        while (!adminViewModel.validateCity(city)) {
            System.out.println("Invalid city! (city should be at least 2 characters long and alphabets only)");
            System.out.print("Enter city name : ");
            city = scanner.nextLine();
        }
        System.out.println("Enter Theatre Name ");
        String theatreName = scanner.nextLine();
        System.out.println("Enter Total Screens");
        int totalScreens = scanner.nextInt();
        List<Screen> screenList = new ArrayList<>();
        for(int i=1; i<=totalScreens; i++){
            System.out.println("Enter screen "+i+" details: ");
            System.out.println("Specify Number of seat rows ");
            int numberOfSeatRows = scanner.nextInt();
            System.out.println("Specify Number of seat cols ");
            int numberOfSeatCols = scanner.nextInt();
            Screen screen = new Screen(i, numberOfSeatRows, numberOfSeatCols, theatreName);
            screenList.add(screen);
            screen.setSeat2D(numberOfSeatRows, numberOfSeatCols);
        }
        if (adminViewModel.addTheatre(city, theatreName, totalScreens, screenList)) {
            System.out.println("Theatre added successfully!");
        } else {
            System.out.println("Theatre could not be added! City might already be present!");
        }
        System.out.println();
    }
}