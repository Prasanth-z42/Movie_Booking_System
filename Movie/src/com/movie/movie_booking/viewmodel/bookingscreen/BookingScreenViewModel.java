package com.movie.movie_booking.viewmodel.bookingscreen;

import com.movie.movie_booking.datalayer.TheatreRepository;
import com.movie.movie_booking.dto.Movie;
import com.movie.movie_booking.dto.Screen;
import com.movie.movie_booking.dto.Ticket;

import java.util.List;

class BookingScreenViewModel {
    private BookingScreen bookingScreen;

    public BookingScreenViewModel(BookingScreen screen) {
        bookingScreen = screen;
    }


    boolean check2(int row, int col, int[][] seat) {
        return seat[row][col] == 0;
    }

    boolean check(int row, int col, int[][] seat) {
        return (row < 0 || row >= seat.length || col < 0 || col >= seat[0].length) || seat[row][col] == 1;
    }



    boolean cancelTicket(int row, int col, Screen screen, String customerNumber) {
        Ticket ticket = screen.getSeats()[row][col];
        if (ticket != null && customerNumber.equals(ticket.getCustomerNumber())) {
            screen.getSeats()[row][col] = null;
            screen.getSeat2D()[row][col] = 0;
        } else {
            return false;
        }
        return true;
    }
    void bookTicket(int row, int col, int[][] seat, Ticket ticket, Ticket[][] tickets) {
        ticket.setSeatNumber(seating(row,col));
        tickets[row][col] = ticket;
        seat[row][col] = 1;
    }

    String seating(int row, int col) {
        String str = (char)(row + 65) + "" +(col+1);
        return str;
    }
}

