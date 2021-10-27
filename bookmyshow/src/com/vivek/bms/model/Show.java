package com.vivek.bms.model;

import java.util.Date;

public class Show {

    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final Date startTime;
    private final Integer durationInSeconds;

    public Show(String id, Movie movie, Screen screen, Date startTime, Integer durationInSeconds) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInSeconds = durationInSeconds;
    }

    public String getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }
}
