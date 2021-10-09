package com.vivek.rental.car.model.reservation;

import com.vivek.rental.car.model.common.Address;
import com.vivek.rental.car.model.common.Coordinates;

public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
