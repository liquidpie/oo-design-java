package com.http.core.ex2.model;

public class User {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;

    private User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.avatar = builder.avatar;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public static final class Builder {
        private int id;
        private String email;
        private String firstName;
        private String lastName;
        private String avatar;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withAvatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
