package com.vivek.bms.api;

import com.vivek.bms.model.Movie;
import com.vivek.bms.model.Screen;
import com.vivek.bms.model.Seat;
import com.vivek.bms.model.Show;
import com.vivek.bms.services.MovieService;
import com.vivek.bms.services.SeatAvailabilityService;
import com.vivek.bms.services.ShowService;
import com.vivek.bms.services.TheatreService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ShowController {
    
    private final SeatAvailabilityService seatAvailabilityService;
    private final ShowService showService;
    private final TheatreService theatreService;
    private final MovieService movieService;

    public ShowController(SeatAvailabilityService seatAvailabilityService, ShowService showService, TheatreService theatreService, MovieService movieService) {
        this.seatAvailabilityService = seatAvailabilityService;
        this.showService = showService;
        this.theatreService = theatreService;
        this.movieService = movieService;
    }

    public String createShow(final String movieId, final String screenId, final Date startTime,
                             final Integer durationInSeconds) {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);
        return showService.createShow(movie, screen, startTime, durationInSeconds).getId();
    }

    public List<String> getAvailableSeats(final String showId) {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
    
}
