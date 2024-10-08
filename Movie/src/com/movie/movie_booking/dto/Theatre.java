package com.movie.movie_booking.dto;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private String city;
    private String name;
    private int totalScreens;
    private List<Screen> screenList;

    public Theatre(String city, String name, int totalScreens, List<Screen> screenList) {
        this.city = city;
        this.name = name;
        this.totalScreens = totalScreens;
        this.screenList = screenList;
    }

//    public void setShows(ArrayList<Show> shows) {
//        this.shows = shows;
//    }

    public int getTotalScreens() {
        return totalScreens;
    }

    public List<Screen> getScreenList() {
        return screenList;
    }

    public void setScreenList(List<Screen> screenList) {
        this.screenList = screenList;
    }


    public void setTotalScreens(int totalScreens) {
        this.totalScreens = totalScreens;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


//
//    public void displayShows() {
//        System.out.println("Shows at " + name + ":");
//        for (int i = 0; i < shows.size(); i++) {
//            System.out.println((i + 1) + ". " + shows.get(i));
//        }
//    }

//    public Show getShow(int index) {
//        if (index >= 0 && index < shows.size()) {
//            return shows.get(index);
//        } else {
//            return null;
//        }
//    }
}
