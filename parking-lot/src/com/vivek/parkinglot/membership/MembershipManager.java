package com.vivek.parkinglot.membership;

import com.vivek.parkinglot.Vehicle;

public interface MembershipManager {

    void subscribe(Vehicle vehicle, Membership membership);

    void unsubscribe(Vehicle vehicle, Membership membership);

}
