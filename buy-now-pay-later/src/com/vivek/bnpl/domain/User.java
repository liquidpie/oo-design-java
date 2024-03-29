package com.vivek.bnpl.domain;

import java.math.BigInteger;
import java.util.Objects;

public class User {

    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private BigInteger creditLimit;

    public User(String id, String firstName, String lastName, String email, BigInteger creditLimit) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.creditLimit = creditLimit;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public BigInteger getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigInteger creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(creditLimit, user.creditLimit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, creditLimit);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", creditLimit=" + creditLimit +
                '}';
    }
}
