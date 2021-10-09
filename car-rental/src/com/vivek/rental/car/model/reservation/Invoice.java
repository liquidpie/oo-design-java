package com.vivek.rental.car.model.reservation;

public class Invoice {
    private String invoiceId;
    private String reservationId;
    private String userId;
    private double usageCharges;
    private double addonCost;
    private double addonServicesCost;
    private double taxes;
    private double total;

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getUsageCharges() {
        return usageCharges;
    }

    public void setUsageCharges(double usageCharges) {
        this.usageCharges = usageCharges;
    }

    public double getAddonCost() {
        return addonCost;
    }

    public void setAddonCost(double addonCost) {
        this.addonCost = addonCost;
    }

    public double getAddonServicesCost() {
        return addonServicesCost;
    }

    public void setAddonServicesCost(double addonServicesCost) {
        this.addonServicesCost = addonServicesCost;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
