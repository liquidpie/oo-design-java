package com.vivek.amz.locker.service;

import com.vivek.amz.locker.TestData;
import com.vivek.amz.locker.repository.*;
import com.vivek.amz.locker.model.*;
import com.vivek.amz.locker.exception.*;
import com.vivek.amz.locker.utils.SizeUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerServiceTest {

    static LockerService lockerService;
    static DeliveryService deliveryService;

    @BeforeAll
    public static void setup() {
        LockerLocationRepository.lockerLocations.add(
                TestData.setupLockerLocation("RMBGBGKAIN",
                        12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(
                TestData.setupLockerLocation("VMBGBGKAIN",
                        12.909953, 77.601866));
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());

        LockerRepository.lockers.addAll(LockerLocationRepository
                .getLockerLocation("RMBGBGKAIN").getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }

        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
        lockerService = new LockerService();
        deliveryService = new DeliveryService();
    }

    @Test
    public void shouldReturnLocker() {
        String lockerId = LockerRepository.lockers.get(0).getId();
        assertNotNull(lockerService.findLockerById(lockerId));
    }

    @Test
    public void shouldGetLocker() {
        Locker locker =
                lockerService.getLocker(LockerSize.XS,
                        new GeoLocation(12.909953, 77.601866));
        assertNotNull(locker);
    }

    @Test
    public void shouldGetLockerSizeForPack() throws PackageSizeMappingException {
        Pack pack = TestData.getPackage();
        LockerSize lockerSize = SizeUtil.getLockerSizeForPack(pack.getPackageSize());
        System.out.println(lockerSize);
        assertNotNull(lockerSize);
    }

    @Test
    public void emulatePickFromLocker() throws
            PackageSizeMappingException, LockerCodeMisMatchException,
            LockerNotFoundException, PackPickTimeExceededException {
        deliveryService.deliver("o1");
        Notification notification = NotificationRepository.notificationMap.get("o1");
        LockerPackage lockerPackage =
                LockerPackageRepository.lockerPackages.stream()
                        .filter(lockerPackage1 -> lockerPackage1.getOrderId().equals("o1"))
                        .findFirst().get();
        lockerService.pickFromLocker(lockerPackage.getLockerId(), lockerPackage.getCode(),
                LocalDateTime.now());

        assertEquals(LockerStatus.AVAILABLE,
                LockerRepository.lockerMap.get(notification.getLockerId()).getLockerStatus());
    }

}
