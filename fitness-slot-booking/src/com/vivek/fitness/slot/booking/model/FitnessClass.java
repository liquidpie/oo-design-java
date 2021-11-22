package com.vivek.fitness.slot.booking.model;

public class FitnessClass {

    private final String id;
    private final FitnessClassType type;
    private final String instructorId;

    public FitnessClass(String id, FitnessClassType type, String instructorId) {
        this.id = id;
        this.type = type;
        this.instructorId = instructorId;
    }

    public String getId() {
        return id;
    }

    public FitnessClassType getType() {
        return type;
    }

    public String getInstructorId() {
        return instructorId;
    }
}
