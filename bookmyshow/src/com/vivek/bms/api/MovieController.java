package com.vivek.bms.api;

import com.vivek.bms.services.MovieService;

public class MovieController {

    final private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public String createMovie(final String movieName) {
        return movieService.createMovie(movieName).getId();
    }

}
