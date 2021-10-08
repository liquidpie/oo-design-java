package com.vivek.amz.locker.model;

import com.vivek.amz.locker.utils.IdGenerator;

public class Locker {

    private String id;
    private LockerSize lockerSize;
    private String locationId;
    private LockerStatus lockerStatus;

    public Locker(LockerSize lockerSize, String locationId) {
        id = IdGenerator.generateId(8);
        this.lockerSize = lockerSize;
        this.locationId = locationId;
        this.lockerStatus = LockerStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LockerSize getLockerSize() {
        return lockerSize;
    }

    public void setLockerSize(LockerSize lockerSize) {
        this.lockerSize = lockerSize;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }

    public void setLockerStatus(LockerStatus lockerStatus) {
        this.lockerStatus = lockerStatus;
    }
}
