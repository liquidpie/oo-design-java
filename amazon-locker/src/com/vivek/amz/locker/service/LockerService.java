package com.vivek.amz.locker.service;

import com.vivek.amz.locker.model.*;
import com.vivek.amz.locker.exception.*;
import com.vivek.amz.locker.repository.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public class LockerService {

    public Locker findLockerById(String id) {
        return LockerRepository.lockerMap.get(id);
    }

    public Locker getLocker(LockerSize lockerSize, GeoLocation geoLocation) {
        return getAvailableLocker(lockerSize, geoLocation);
    }

    public void pickFromLocker(String lockerId,
                               String code, LocalDateTime localDateTime) throws
            LockerNotFoundException, LockerCodeMisMatchException, PackPickTimeExceededException,
            PickupCodeExpiredException {
        Optional<LockerPackage> lockerPackage =
                LockerPackageRepository.getLockerPackageByLockerId(lockerId);
        if (lockerPackage.isEmpty())
            throw new LockerNotFoundException("Locker with code not found");
        if (!lockerPackage.get().verifyCode(code))
            throw new LockerCodeMisMatchException("Locker code mismatch");
        if (!lockerPackage.get().isValidCode(localDateTime)) {
            throw new PickupCodeExpiredException("Pickup code expired");
        }
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        if (canPickFromLocker(lockerId, localDateTime)) {
            locker.setLockerStatus(LockerStatus.AVAILABLE);
            lockerPackage.get().setCode(null);
        } else {
            lockerPackage.get().setCode(null);
            throw new PackPickTimeExceededException("Package not picked for x days");
        }
    }

    private Locker getAvailableLocker(LockerSize lockerSize,
                                      GeoLocation geoLocation) {
        return checkAndGetAvailableLockers(lockerSize, geoLocation);
    }

    private Locker checkAndGetAvailableLockers(LockerSize lockerSize,
                                               GeoLocation geoLocation) {
        Locker locker = LockerRepository.getLocker(lockerSize, geoLocation);
        locker.setLockerStatus(LockerStatus.BOOKED);
        return locker;
    }

    private boolean canPickFromLocker(String lockerId, LocalDateTime localDateTime) {
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        LockerLocation lockerLocation = LockerLocationRepository.getLockerLocation(locker.getLocationId());
        LocationTiming locationTiming = lockerLocation.getLocationTiming();
        Timing timing = locationTiming.getTimingMap().get(localDateTime.getDayOfWeek());
        Time currentTime = Time.valueOf(getTimeFromDate(localDateTime));
        return currentTime.after(timing.getOpenTime()) && currentTime.before(timing.getCloseTime());
    }

    private String getTimeFromDate(LocalDateTime localDateTime) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        return localDateFormat.format(new Date());
    }

}
