package com.movie.movie_booking.viewmodel.bookingscreen;


import com.movie.movie_booking.dto.*;
import com.movie.movie_booking.validate.Validator;
import com.movie.movie_booking.viewmodel.BaseScreen;

import java.util.List;
import java.util.Scanner;

public class BookingScreen extends BaseScreen {
    private BookingScreenViewModel bookingScreenViewModel;
    private Scanner scanner;
    private Validator validator;

    public BookingScreen() {
        bookingScreenViewModel = new BookingScreenViewModel(this);
        this.scanner = new Scanner(System.in);
        this.validator = new Validator();
    }


    public void bookTicket(List<Show> shows, Screen screen) {
        if (shows.size() == 0) {
            System.out.println("Shows are not available");
            return;
        }
        displayShows(shows);
        System.out.println("Enter show Number");
        int showNumber = scanner.nextInt()-1;
        scanner.nextLine();

        Show show = shows.get(showNumber);
        displayShow(show);
        System.out.println("Available Seats (O)");
        displaySeats(screen.getSeat2D());

        System.out.println("Enter Number of Seats");
        int tickets = scanner.nextInt();
        scanner.nextLine();
        while (tickets-- > 0) {
            System.out.println("Enter Seat : " + (tickets+1));
            while (true) {
                System.out.println("Enter row number");
                int row = scanner.nextInt()-1;
                scanner.nextLine();
                System.out.println("enter col number");
                int col = scanner.nextInt()-1;
                scanner.nextLine();

                if (bookingScreenViewModel.check(row, col, screen.getSeat2D())) {
                    System.out.println("Invalid Seat Number... Please enter correct row and col");
                } else {
                    String customerName = "";
                    String customerNumber = "";
                    while (true) {
                        System.out.println("Enter Name");
                        customerName = scanner.nextLine();
                        if (!validator.validateName(customerName)) {
                            System.out.println("Enter valid name");
                            return;
                        }
                        else break;
                    }
                    while (true) {
                        System.out.println("Enter Phone Number");
                        customerNumber = scanner.nextLine();
                        if (!validator.validatePhoneNumber(customerNumber)) {
                            System.out.println("Enter valid Phone Number");
                            return;
                        }
                        else break;
                    }
                    Ticket ticket = new Ticket(customerName, customerNumber);
                    bookingScreenViewModel.bookTicket(row, col, screen.getSeat2D(), ticket, screen.getSeats());
                    displayTicketDetails(screen, ticket, show);
                    break;
                }
            }
        }
        System.out.println("Tickets booked Successfully");
        show.setAvailableSeats(show.getAvailableSeats()-1);
        displaySeats(screen.getSeat2D());
    }

    private void displayTicketDetails(Screen screen, Ticket ticket, Show show) {
        System.out.println("----------------------------------------");
        System.out.println("Customer Name : "+ticket.getCustomerName());
        System.out.println("Customer Number : "+ticket.getCustomerNumber());
        System.out.println("Seat Number : "+ticket.getSeatNumber());
        System.out.println("Movie Name : " + show.getMovie().getTitle());
        System.out.println("Screen Number : " + screen.getName());
        System.out.println("----------------------------------------");

    }

    public void cancelTicket(List<Show> shows, Screen screen) {
        if (shows.size() == 0) {
            System.out.println("Shows are not available");
            return;
        }
        displayShows(shows);
        System.out.println("Enter show Number");
        int showNumber = scanner.nextInt()-1;
        scanner.nextLine();

        Show show = shows.get(showNumber);
        displayShow(show);
        displaySeats(screen.getSeat2D());

        System.out.println("enter row number");
        int row = scanner.nextInt()-1;
        scanner.nextLine();
        System.out.println("enter col number");
        int col = scanner.nextInt()-1;
        scanner.nextLine();

        if (bookingScreenViewModel.check(row, col, screen.getSeat2D())) {
            System.out.println("Invalid row and col... Please enter correct row and col...!");
        } else if (bookingScreenViewModel.check2(row, col, screen.getSeat2D())){
            System.out.println("Invalid Seat Number... No one is booking to this seat");
        } else {
            System.out.println("Enter Customer phone Number");
            String customerNumber = scanner.nextLine();
            if (bookingScreenViewModel.cancelTicket(row, col, screen, customerNumber)) {
                System.out.println("Cancelled Successfully...");
                show.setAvailableSeats(show.getAvailableSeats()+1);
                displaySeats(screen.getSeat2D());
            } else {
                System.out.println("Invalid Phone Number..!");
            }
        }
    }


    private void displaySeats(int[][] seat2D) {
        for (int i = 0; i<seat2D.length; i++) {
            for (int j = 0; j<seat2D[i].length; j++) {
                System.out.print(seat2D[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void displayShows(List<Show> shows) {
        int k = 1;
        for (Show show : shows) {
            System.out.println("Show Number : " + k++);
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
}
