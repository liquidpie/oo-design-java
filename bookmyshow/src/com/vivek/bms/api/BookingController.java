package com.vivek.bms.api;

import com.vivek.bms.model.Seat;
import com.vivek.bms.model.Show;
import com.vivek.bms.services.*;

import java.util.List;
import java.util.stream.Collectors;

public class BookingController {
    
    private final ShowService showService;
    private final BookingService bookingService;
    private final TheatreService theatreService;

    public BookingController(ShowService showService, BookingService bookingService, TheatreService theatreService) {
        this.showService = showService;
        this.bookingService = bookingService;
        this.theatreService = theatreService;
    }

    public String createBooking(final String userId, final String showId,
                                final List<String> seatsIds) {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatsIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats).getId();
    }
}
