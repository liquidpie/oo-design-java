package com.http.core.ex2.model;

public class UserRequest {

    private String firstName;
    private String lastName;

    public UserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
