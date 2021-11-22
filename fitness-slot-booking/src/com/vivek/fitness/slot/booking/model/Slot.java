package com.vivek.fitness.slot.booking.model;

import java.time.LocalDateTime;

public class Slot {

    private final String slotId;
    private final FitnessClass fitnessClass;
    private final LocalDateTime dateTime;
    private final int totalCapacity;

    public Slot(String slotId, FitnessClass fitnessClass, LocalDateTime dateTime, int totalCapacity) {
        this.slotId = slotId;
        this.fitnessClass = fitnessClass;
        this.dateTime = dateTime;
        this.totalCapacity = totalCapacity;
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

    @Override
    public String toString() {
        return "Slot{" +
                "slotId='" + slotId + '\'' +
                ", fitnessClass=" + fitnessClass +
                ", dateTime=" + dateTime +
                ", totalCapacity=" + totalCapacity +
                '}';
    }
}
