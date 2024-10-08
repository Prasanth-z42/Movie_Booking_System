package com.movie.movie_booking.datalayer;

import com.movie.movie_booking.dto.Movie;
import com.movie.movie_booking.dto.Theatre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreRepository {
    private static TheatreRepository repository;
    private List<String> cities = new ArrayList<>();
    private Map<String, List<Theatre>> cityTheatreMap = new HashMap<>();
    private Map<Integer, List<Theatre>> movieIdTheatreMap = new HashMap<>();

    private Map<String, Theatre> theatreMap = new HashMap<>();
    private List<Movie> movieList = new ArrayList<>();
    private TheatreRepository() {

    }

    public Map<String, Theatre> getTheatreMap() {
        return theatreMap;
    }

    public void setTheatreMap(Map<String, Theatre> theatreMap) {
        this.theatreMap = theatreMap;
    }

    public Map<String, List<Theatre>> getCityTheatreMap() {
        return cityTheatreMap;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public void setCityTheatreMap(Map<String, List<Theatre>> cityTheatreMap) {
        this.cityTheatreMap = cityTheatreMap;
    }

    public static TheatreRepository getInstance() {

        if (repository == null) {
            repository = new TheatreRepository();
        }

        return repository;
    }

    public List<String> getCities() {
        return cities;
    }
    public boolean addCity(String city) {
        if (!cities.contains(city.toLowerCase())) {
            cities.add(city.toLowerCase());
            return true;
        } else {
            return false;
        }
    }
    public void addMoveIdToTheatre(int movieId, Theatre theatre) {
        if(movieIdTheatreMap.containsKey(movieId)){
            movieIdTheatreMap.get(movieId).add(theatre);
        }
        else{
            List<Theatre> theatreList = new ArrayList<>();
            theatreList.add(theatre);
            movieIdTheatreMap.put(movieId, theatreList);
        }
    }

    public boolean createTheatre(String city, Theatre theatre) {
        List<Theatre> list = new ArrayList<>();
        list = TheatreRepository.getInstance().cityTheatreMap.get(city);
        list.add(theatre);
        return true;
    }
//
//    public Theatre getTheatreOfACity(String city) {
//        return cityTheatreMap.getOrDefault(city, null);
//    }
    public List<Theatre> getTheatresRunningShow(int selectedMovieId) {
        if (movieIdTheatreMap.containsKey(selectedMovieId)) {
            return movieIdTheatreMap.get(selectedMovieId);
        } else {
            return new ArrayList<>();
        }
    }


    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovies() {
        return movieList;
    }
   public List<Theatre> getAllTheatres(){
        return new ArrayList<>();
    }
//    public Map<String, Theatre> getAllTheatresWithCity(){
  //      return cityTheatreMap;
    //}

}
