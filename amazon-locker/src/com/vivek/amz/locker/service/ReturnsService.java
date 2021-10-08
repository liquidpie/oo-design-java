package com.vivek.amz.locker.service;

import com.vivek.amz.locker.model.Item;
import com.vivek.amz.locker.model.Locker;
import com.vivek.amz.locker.model.LockerStatus;

public class ReturnsService {

    public void returnItemsToLocker(Item item, Locker locker) {
        locker.setLockerStatus(LockerStatus.CLOSED);
    }

}

