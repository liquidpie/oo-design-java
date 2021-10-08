package com.vivek.amz.locker.repository;

import com.vivek.amz.locker.model.LockerPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LockerPackageRepository {

    public static List<LockerPackage> lockerPackages = new ArrayList<>();

    public static Optional<LockerPackage> getLockerPackageByLockerId(String lockerId) {
        return lockerPackages.stream()
                .filter(lockerPackage -> lockerPackage.getLockerId().equals(lockerId))
                .findFirst();
    }

}
