package com.vivek.fitness.slot.booking.udaan;

import com.vivek.fitness.slot.booking.controller.FitnessSlotBookingController;
import com.vivek.fitness.slot.booking.dao.Database;
import com.vivek.fitness.slot.booking.model.*;
import com.vivek.fitness.slot.booking.service.FitnessClassService;
import com.vivek.fitness.slot.booking.service.SlotService;
import com.vivek.fitness.slot.booking.service.WaitlistService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FitnessApplication {

    public static void main(String[] args) {
        // Dependencies
        FitnessClassService fcs = new FitnessClassService();
        WaitlistService waitlistService = new WaitlistService();
        SlotService slotService = new SlotService(waitlistService);
        FitnessSlotBookingController controller = new FitnessSlotBookingController(fcs, slotService);

        // Users
        List<User> users = new ArrayList<>(Database.USERS.values());
        User u1 = users.get(0);
        User u2 = users.get(1);
        User u3 = users.get(2);

        // Usecase: user can look for available classes
        Map<FitnessClassType, FitnessClass> fitnessClasses = controller.getAvailableClasses();
        FitnessClass yogaClass = fitnessClasses.get(FitnessClassType.YOGA);

        // Usecase: user can look for slots
        List<Slot> slots = controller.getSlots(yogaClass.getId());
        Slot slot1 = slots.get(0);

        // Usecase: user can book an available slot
        SlotBooking b1 = controller.bookSlot(slot1.getSlotId(), u1.getId());
        SlotBooking b2 = controller.bookSlot(slot1.getSlotId(), u2.getId());
        SlotBooking b3 = controller.bookSlot(slot1.getSlotId(), u3.getId());

        // Usecase: user can cancel a slot
        boolean status = controller.cancelBooking(b1.getBookingId());

        System.out.println(status);

        // Usecase: find all bookings
        System.out.println(Database.BOOKINGS);
    }

}
