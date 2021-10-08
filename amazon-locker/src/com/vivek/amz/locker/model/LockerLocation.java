package com.vivek.amz.locker.model;

import java.util.ArrayList;
import java.util.List;

public class LockerLocation {

    private String locationId;
    private List<Locker> lockers = new ArrayList<>();
    private GeoLocation geoLocation;
    private LocationTiming locationTiming;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public List<Locker> getLockers() {
        return lockers;
    }

    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public LocationTiming getLocationTiming() {
        return locationTiming;
    }

    public void setLocationTiming(LocationTiming locationTiming) {
        this.locationTiming = locationTiming;
    }
}
