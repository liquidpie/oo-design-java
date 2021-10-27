package com.vivek.movie.booking.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CinemaHall {

    int cinemaHallId;
    String name;
    Address address;
    List<Auditorium> auditoriums;

    public Map<Date, List<Movie>> getMovies(List<Date> dates) {
        return null;
    }

    public Map<Date, List<Show>> getShows(List<Date> dates) {
        return null;
    }

}
