package com.vivek.vending.machine.service;

import com.vivek.vending.machine.model.ItemSlot;

import java.math.BigDecimal;
import java.util.List;

/**
 * To be implemented by proxy classes. All service classes are created as singleton
 */
public interface InventoryService {

    List<ItemSlot> initializeItems();

    BigDecimal getSelectedItemPrice(ItemSlot itemSlot);

}
