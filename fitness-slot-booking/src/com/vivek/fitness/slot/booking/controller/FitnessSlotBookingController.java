package com.vivek.fitness.slot.booking.controller;

import com.vivek.fitness.slot.booking.model.FitnessClass;
import com.vivek.fitness.slot.booking.model.FitnessClassType;
import com.vivek.fitness.slot.booking.model.Slot;
import com.vivek.fitness.slot.booking.model.SlotBooking;
import com.vivek.fitness.slot.booking.service.FitnessClassService;
import com.vivek.fitness.slot.booking.service.SlotService;

import java.util.List;
import java.util.Map;

public class FitnessSlotBookingController {

    private final FitnessClassService fitnessClassService;
    private final SlotService slotService;

    public FitnessSlotBookingController(FitnessClassService fitnessClassService, SlotService slotService) {
        this.fitnessClassService = fitnessClassService;
        this.slotService = slotService;
    }

    public Map<FitnessClassType, FitnessClass> getAvailableClasses() {
        return fitnessClassService.getAvailableClasses();
    }

    public List<Slot> getSlots(String fitnessClassId) {
        return slotService.getSlots(fitnessClassId);
    }

    public SlotBooking bookSlot(String slotId, String userId) {
        return slotService.bookSlot(slotId, userId);
    }

    public boolean cancelBooking(String bookingId) {
        return slotService.cancelBooking(bookingId);
    }

}
