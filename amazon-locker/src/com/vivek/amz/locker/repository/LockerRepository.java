package com.vivek.amz.locker.repository;

import com.vivek.amz.locker.model.GeoLocation;
import com.vivek.amz.locker.model.Locker;
import com.vivek.amz.locker.model.LockerSize;
import com.vivek.amz.locker.model.LockerStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LockerRepository {

    public static List<Locker> lockers;
    public static Map<String, Locker> lockerMap;

    static {
        lockers = new ArrayList<>();
        lockerMap = new HashMap<>();
    }

    public static Locker getLocker(LockerSize lockerSize, GeoLocation location) {
        //assumption the near by lockers in radius are fetched from a service

        List<Locker> lockerList =
                lockers.stream()
                        .filter(locker ->
                                locker.getLockerStatus() == LockerStatus.AVAILABLE &&
                                        locker.getLockerSize() == lockerSize)
                        .collect(Collectors.toList());
        if (lockerList.size() > 0)
            return lockerList.get(0);
        return null;
    }

}
