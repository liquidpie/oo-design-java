package com.vivek.movie.booking.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Show {

    int showId;
    Movie movie;
    LocalDateTime startTime;
    LocalDateTime endTime;
    CinemaHall cinemaHallPlayedAt;
    List<Seat> seats;

}
