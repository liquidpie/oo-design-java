package com.vivek.vending.machine.service;

import com.vivek.vending.machine.model.ItemSlot;

import java.time.LocalDateTime;

/**
 * To be implemented by proxy classes. All service classes are created as singleton
 */
public interface LoggingService {

    void logPurchase(ItemSlot itemSlot, LocalDateTime purchaseDateTime);

}
