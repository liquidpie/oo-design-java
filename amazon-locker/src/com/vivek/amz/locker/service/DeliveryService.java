package com.vivek.amz.locker.service;

import com.vivek.amz.locker.exception.PackageSizeMappingException;
import com.vivek.amz.locker.model.*;
import com.vivek.amz.locker.repository.LockerPackageRepository;
import com.vivek.amz.locker.utils.IdGenerator;
import com.vivek.amz.locker.utils.SizeUtil;

import java.util.List;

public class DeliveryService {

    NotificationService notificationService = new NotificationService();
    OrderService orderService = new OrderService();
    LockerService lockerService = new LockerService();

    public void deliver(String orderId) throws PackageSizeMappingException {
        Order order = orderService.getOrder(orderId);
        List<Item> items = orderService.getItemsForOrder(orderId);
        Pack pack = new Pack(orderId, items);
        LockerSize lockerSize = SizeUtil.getLockerSizeForPack(pack.getPackageSize());
        Locker locker = lockerService.getLocker(lockerSize, order.getDeliveryGeoLocation());
        LockerPackage lockerPackage = new LockerPackage();
        lockerPackage.setOrderId(orderId);
        lockerPackage.setLockerId(locker.getId());
        lockerPackage.setCode(IdGenerator.generateId(6));
        LockerPackageRepository.lockerPackages.add(lockerPackage);
        locker.setLockerStatus(LockerStatus.CLOSED);
        notificationService.notifyCustomerOrder(lockerPackage);
    }

}
