package com.vivek.fitness.slot.booking.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Queue;

/**
 * This composite Slot class can help in concurrency control
 */
public class CompositeSlot {

    private final String slotId;
    private final FitnessClass fitnessClass;
    private final LocalDateTime dateTime;
    private final int totalCapacity;

    private final List<SlotBooking> bookings;
    private final Queue<WaitlistRequest> waitlistRequests;

    public CompositeSlot(String slotId, FitnessClass fitnessClass, LocalDateTime dateTime, int totalCapacity, List<SlotBooking> bookings, Queue<WaitlistRequest> waitlistRequests) {
        this.slotId = slotId;
        this.fitnessClass = fitnessClass;
        this.dateTime = dateTime;
        this.totalCapacity = totalCapacity;
        this.bookings = bookings;
        this.waitlistRequests = waitlistRequests;
    }

    public String getSlotId() {
        return slotId;
    }

    public FitnessClass getFitnessClass() {
        return fitnessClass;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public List<SlotBooking> getBookings() {
        return bookings;
    }

    public Queue<WaitlistRequest> getWaitlistRequests() {
        return waitlistRequests;
    }
}
