package com.vivek.rental.car.model.vehicle;

import com.vivek.rental.car.model.common.Address;
import com.vivek.rental.car.model.common.Coordinates;

public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
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
