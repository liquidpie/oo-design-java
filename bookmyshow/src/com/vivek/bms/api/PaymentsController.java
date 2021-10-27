package com.vivek.bms.api;

import com.vivek.bms.services.BookingService;
import com.vivek.bms.services.PaymentsService;

public class PaymentsController {
    private final PaymentsService paymentsService;
    private final BookingService bookingService;

    public PaymentsController(PaymentsService paymentsService, BookingService bookingService) {
        this.paymentsService = paymentsService;
        this.bookingService = bookingService;
    }

    public void paymentFailed(final String bookingId, final String user) {
        paymentsService.processPaymentFailed(bookingService.getBooking(bookingId), user);
    }

    public void paymentSuccess(final  String bookingId, final String user) {
        bookingService.confirmBooking(bookingService.getBooking(bookingId), user);
    }

}
