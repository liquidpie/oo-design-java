package com.vivek.fitness.slot.booking.model;

import java.time.LocalDateTime;

public class SlotBooking {

    private final String bookingId;
    private final String slotId;
    private final String bookedBy;
    private final LocalDateTime bookedAt;
    private BookingStatus status;

    public SlotBooking(String bookingId, String slotId, String bookedBy, LocalDateTime bookedAt) {
        this.bookingId = bookingId;
        this.slotId = slotId;
        this.bookedBy = bookedBy;
        this.bookedAt = bookedAt;
        this.status = BookingStatus.BOOKED;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SlotBooking{" +
                "bookingId='" + bookingId + '\'' +
                ", slotId='" + slotId + '\'' +
                ", bookedBy='" + bookedBy + '\'' +
                ", bookedAt=" + bookedAt +
                ", status=" + status +
                '}';
    }
}
