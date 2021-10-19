package com.vivek.parkinglot.membership;

import com.vivek.parkinglot.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotMembershipManager implements MembershipManager {

    private final Map<String, Membership> subscribedVehicles;

    public ParkingLotMembershipManager() {
        this.subscribedVehicles = new HashMap<>();
    }

    @Override
    public void subscribe(Vehicle vehicle, Membership membership) {
        subscribedVehicles.put(vehicle.getLicense(), membership);
    }

    @Override
    public void unsubscribe(Vehicle vehicle, Membership membership) {
        subscribedVehicles.remove(vehicle.getLicense());
    }
}
