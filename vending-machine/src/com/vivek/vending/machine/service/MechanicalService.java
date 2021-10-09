package com.vivek.vending.machine.service;

import com.vivek.vending.machine.model.ItemSlot;
import com.vivek.vending.machine.model.Payment;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interacts with hardware
 * To be implemented by proxy classes. All service classes are created as singleton
 */
public interface MechanicalService {

    Payment takePayment();
    void returnChanges(BigDecimal bigDecimal);
    void releaseItem(ItemSlot itemSlot);
    void displayAvailableItems(List<ItemSlot> itemSlots);

}
