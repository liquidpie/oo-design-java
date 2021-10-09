package com.vivek.vending.machine;

import com.vivek.vending.machine.model.ItemSlot;
import com.vivek.vending.machine.model.Payment;
import com.vivek.vending.machine.service.InventoryService;
import com.vivek.vending.machine.service.LoggingService;
import com.vivek.vending.machine.service.MechanicalService;
import com.vivek.vending.machine.service.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class VendingMachine {

    private final InventoryService inventoryService;
    private final LoggingService loggingService;
    private final PaymentService paymentService;
    private final MechanicalService mechanicalService;

    private List<ItemSlot> locationsOfAvailableItems;
    private ItemSlot selectedItem;
    private BigDecimal amountOwning;

    public VendingMachine(InventoryService inventoryService, LoggingService loggingService, PaymentService paymentService, MechanicalService mechanicalService) {
        this.inventoryService = inventoryService;
        this.loggingService = loggingService;
        this.paymentService = paymentService;
        this.mechanicalService = mechanicalService;
    }

    /*This method is called when the vending machine is powered on*/
    private void initVendingMachine() {
        locationsOfAvailableItems = inventoryService.initializeItems();
        mechanicalService.displayAvailableItems(locationsOfAvailableItems);
        selectedItem = null;
        amountOwning = BigDecimal.ZERO;
    }

    private void productSelectedListener(ItemSlot itemSlot) {
        amountOwning = inventoryService.getSelectedItemPrice(itemSlot);
    }

    private void paymentMadeListener(Payment payment) {
        BigDecimal amountPaid = paymentService.makePayment(payment);
        amountOwning = amountOwning.subtract(amountPaid);
        if(amountOwning.compareTo(BigDecimal.ZERO) > 0) {
            processPurchase();
        }
    }

    private void processPurchase() {
        loggingService.logPurchase(selectedItem, LocalDateTime.now());
        returnChange(amountOwning);
        releaseProduct();
        resetSelectedProduct();
    }

    private void resetSelectedProduct() {
        selectedItem = null;
        amountOwning = BigDecimal.ZERO;
    }

    private void returnChange(BigDecimal changes) {
        mechanicalService.returnChanges(changes);
    }

    private void releaseProduct() {
        mechanicalService.releaseItem(selectedItem);
    }
    
}
