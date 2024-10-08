package com.movie.movie_booking.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Screen {
    private int id;
    private int capacity;
    private String name;
    private int seatRow;
    private int seatCol;
    private int[][] seat2D;
    private Ticket[][] seats;
    private List<Show> shows;
    private String theatreName;
    public Screen(){

    }
    public Screen(int id, int seatRow, int seatCol, String theatreName) {
        this.name = "Screen"+id;
        this.seatRow = seatRow;
        this.seatCol = seatCol;
        this.capacity = seatRow * seatCol;
        this.seat2D = new int[seatRow][seatCol];
        this.seats = new Ticket[seatRow][seatCol];
        this.shows = new ArrayList<>();
        this.theatreName = theatreName;
    }

    public void setSeat2D(int[][] seat2D) {
        this.seat2D = seat2D;
    }

    public Ticket[][] getSeats() {
        return seats;
    }

    public void setSeats(Ticket[][] seats) {
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    public int[][] getSeat2D() {
        return seat2D;
    }

    public void setSeat2D(int row, int col) {
        for (int i = 0; i<row; i++) {
            Arrays.fill(seat2D[i], 0);
        }
    }

    public List<Show> getShows() {
        return shows;
    }

    public void addShows(Show show) {
        this.shows.add(show);
    }
    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

}
