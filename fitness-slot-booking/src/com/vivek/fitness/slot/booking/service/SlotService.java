package com.vivek.fitness.slot.booking.service;

import com.vivek.fitness.slot.booking.dao.Database;
import com.vivek.fitness.slot.booking.model.BookingStatus;
import com.vivek.fitness.slot.booking.model.Slot;
import com.vivek.fitness.slot.booking.model.SlotBooking;
import com.vivek.fitness.slot.booking.model.WaitlistRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SlotService {

    private final WaitlistService waitlistService;

    public SlotService(WaitlistService waitlistService) {
        this.waitlistService = waitlistService;
    }

    public List<Slot> getSlots(String fitnessClassId) {
        List<String> slotIds = Database.FITNESS_CLASS_SLOTS.get(fitnessClassId);
        List<Slot> slots = new ArrayList<>();
        if (slotIds != null && !slotIds.isEmpty()) {
            slots.addAll(slotIds.stream().map(Database.SLOTS::get).collect(Collectors.toList()));
        }
        return slots;
    }

    public SlotBooking bookSlot(String slotId, String userId) {
        Slot slot = Database.SLOTS.get(slotId);
        if (slotId == null)
            throw new IllegalArgumentException("Invalid Slot Id");
        int totalCapacity = slot.getTotalCapacity();
        List<SlotBooking> slotBookings = Database.SLOT_BOOKINGS.get(slotId);
        if (slotBookings == null) {
            slotBookings = new ArrayList<>();
        }
        int currentBookingCount = slotBookings.size();
        if (currentBookingCount < totalCapacity) {
            String id = UUID.randomUUID().toString();
            SlotBooking booking = new SlotBooking(id, slotId, userId, LocalDateTime.now());
            Database.BOOKINGS.put(booking.getBookingId(), booking);
            List<SlotBooking> existingBookings = Database.SLOT_BOOKINGS.get(slotId);
            if (existingBookings == null) {
                existingBookings = new ArrayList<>();
            }
            existingBookings.add(booking);
            Database.SLOT_BOOKINGS.put(slotId, existingBookings);
            return booking;
        } else {
            waitlistService.addToWaitlist(new WaitlistRequest(slotId, userId));
        }
        return null;
    }

    public boolean cancelBooking(String bookingId) {
        SlotBooking booking = Database.BOOKINGS.get(bookingId);
        Slot slot = Database.SLOTS.get(booking.getSlotId());
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime cancellationPeriod = slot.getDateTime().minusMinutes(30);
        if (!currentTime.isBefore(cancellationPeriod)) {
            return false;
        }

        booking.setStatus(BookingStatus.CANCELLED);
        List<SlotBooking> bookings = Database.SLOT_BOOKINGS.get(slot.getSlotId());
        bookings.removeIf(slotBooking -> slotBooking.getBookingId().equals(bookingId));

        // Serve waitlist request
        WaitlistRequest request = waitlistService.getWaitlistRequest(booking.getSlotId());
        if (request != null) {
            SlotBooking newBooking = bookSlot(request.getSlotId(), request.getUserId());
            if (newBooking == null) {
                waitlistService.addToWaitlist(request);
            }
        }

        return true;
    }

}
