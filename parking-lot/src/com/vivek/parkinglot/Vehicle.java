package com.vivek.parkinglot;

public class Vehicle {

    private final int size;
    private final String license;
    private boolean status;
    private Lot lot;

    public Vehicle(int size) {
        this.size = size;
        license = String.valueOf(this.hashCode());
        lot = Lot.getInstance();
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private Slot findSlot() {
        Slot slot;

        switch (this.size) {
            case 1:
                slot = lot.getSmallSlots().remove(0);
                break;
            case 2:
                slot = lot.getCompactSlots().remove(0);
                break;
            case 3:
                slot = lot.getLargeSlots().remove(0);
                break;
            default:
                slot = null;
        }
        return slot;
    }

    public void park() {
        Slot slot = findSlot();
        if (slot != null) {
            lot.occupiedSlots.put(this.license, slot);
            slot.occupy(this);
            System.out.println("Occupied Slot: " + slot);
        }
    }

    public void leave() {
        Slot slot = lot.occupiedSlots.remove(this.license);
        slot.release();
        switch (this.size) {
            case 1:
                lot.getSmallSlots().add(slot);
                break;
            case 2:
                lot.getCompactSlots().add(slot);
                break;
            case 3:
                lot.getLargeSlots().add(slot);
                break;
        }
        System.out.println("Released Slot: " + slot);
    }

}
