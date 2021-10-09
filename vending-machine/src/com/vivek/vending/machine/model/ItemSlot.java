package com.vivek.vending.machine.model;

public class ItemSlot {

    private final String slotLabel;
    private final int row;
    private final int column;

    public ItemSlot(String slotLabel, int row, int column) {
        this.slotLabel = slotLabel;
        this.row = row;
        this.column = column;
    }

    public String getSlotLabel() {
        return slotLabel;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
