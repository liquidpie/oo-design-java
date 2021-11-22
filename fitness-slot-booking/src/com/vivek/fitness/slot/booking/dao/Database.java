package com.vivek.fitness.slot.booking.dao;

import com.vivek.fitness.slot.booking.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Database {

    public static Map<String, User> USERS = new HashMap<>();
    public static Map<String, FitnessClass> FITNESS_CLASSES = new HashMap<>();
    public static Map<String, List<String>> FITNESS_CLASS_SLOTS = new HashMap<>();
    public static Map<String, Slot> SLOTS = new HashMap<>();
    public static Map<String, List<SlotBooking>> SLOT_BOOKINGS = new HashMap<>();
    public static Map<String, SlotBooking> BOOKINGS = new HashMap<>();

    static {
        // seed users
        User u1 = new User("1", "u1", "123", "u1@example.com");
        User u2 = new User("2", "u2", "123", "u1@example.com");
        User u3 = new User("3", "u3", "123", "u1@example.com");
        USERS.put(u1.getId(), u1);
        USERS.put(u2.getId(), u2);
        USERS.put(u3.getId(), u3);

        // seed classes
        FitnessClass fc1 = new FitnessClass("1", FitnessClassType.YOGA, "instructor1");
        FitnessClass fc2 = new FitnessClass("2", FitnessClassType.GYM, "instructor2");
        FitnessClass fc3 = new FitnessClass("3", FitnessClassType.DANCE, "instructor3");
        FITNESS_CLASSES.put(fc1.getId(), fc1);
        FITNESS_CLASSES.put(fc2.getId(), fc2);
        FITNESS_CLASSES.put(fc3.getId(), fc3);

        // seed slots
        Slot s1 = new Slot("s1", fc1, LocalDateTime.of(2021, 11, 22, 18, 30), 2);
        Slot s2 = new Slot("s2", fc2, LocalDateTime.of(2021, 11, 23, 16, 30), 2);
        Slot s3 = new Slot("s3", fc3, LocalDateTime.of(2021, 11, 23, 17, 30), 2);
        SLOTS.put(s1.getSlotId(), s1);
        FITNESS_CLASS_SLOTS.put(s1.getFitnessClass().getId(), List.of(s1.getSlotId()));
        SLOTS.put(s2.getSlotId(), s2);
        FITNESS_CLASS_SLOTS.put(s2.getFitnessClass().getId(), List.of(s2.getSlotId()));
        SLOTS.put(s3.getSlotId(), s3);
        FITNESS_CLASS_SLOTS.put(s3.getFitnessClass().getId(), List.of(s3.getSlotId()));

    }


}
