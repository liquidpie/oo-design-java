package com.vivek.amz.locker.service;

import com.vivek.amz.locker.TestData;
import com.vivek.amz.locker.exception.PackageSizeMappingException;
import com.vivek.amz.locker.model.Locker;
import com.vivek.amz.locker.model.Notification;
import com.vivek.amz.locker.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryServiceTest {
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
        deliveryService = new DeliveryService();
    }

    @Test
    public void emulateDelivery() throws
            PackageSizeMappingException {
        deliveryService.deliver("o1");
        Notification notification = NotificationRepository.notificationMap.get("o1");
        assertEquals("o1", notification.getOrderId());
    }
}
