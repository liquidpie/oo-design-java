package com.vivek.fitness.slot.booking.model;

public class User {

    private final String id;
    private final String name;
    private final String phoneNumber;
    private final String email;

    public User(String id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}
