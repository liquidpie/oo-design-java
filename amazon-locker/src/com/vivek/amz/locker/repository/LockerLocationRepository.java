package com.vivek.amz.locker.repository;

import com.vivek.amz.locker.model.LockerLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LockerLocationRepository {

    public static List<LockerLocation> lockerLocations = new ArrayList<>();

    public static LockerLocation getLockerLocation(String locationId) {
        Optional<LockerLocation> lockerLocation =
                lockerLocations.stream()
                        .filter(ll -> ll.getLocationId().equals(locationId)).findFirst();
        return lockerLocation.orElse(null);
    }

}
