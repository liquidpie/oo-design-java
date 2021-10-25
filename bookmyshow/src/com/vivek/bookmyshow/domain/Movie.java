package com.vivek.bookmyshow.domain;

import com.vivek.bookmyshow.enums.Genre;
import com.vivek.bookmyshow.enums.LanguageCode;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Movie {

    int movieId;
    String movieName;
    int durationInMins;
    LanguageCode language;
    Genre genre;
    Date releaseDate;
    Map<String, List<Show>> cityShowMap; // movie is running in which city and in which shows

}
