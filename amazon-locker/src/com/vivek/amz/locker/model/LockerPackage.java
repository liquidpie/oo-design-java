package com.vivek.amz.locker.model;

import com.vivek.amz.locker.exception.PickupCodeExpiredException;

import java.time.LocalDateTime;

public class LockerPackage {

    final int codeValidDays = 3;
    private String lockerId;
    private String orderId;
    private String code;
    private LocalDateTime packageDeliveredTime;

    public boolean isValidCode(LocalDateTime currentTime) throws PickupCodeExpiredException {
        if (currentTime.compareTo(packageDeliveredTime) > 3) {
            throw new PickupCodeExpiredException("Pickup code expired.");
        }
        return true;
    }

    public boolean verifyCode(String code) {
        return this.code.equals(code);
    }

    public int getCodeValidDays() {
        return codeValidDays;
    }

    public String getLockerId() {
        return lockerId;
    }

    public void setLockerId(String lockerId) {
        this.lockerId = lockerId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getPackageDeliveredTime() {
        return packageDeliveredTime;
    }

    public void setPackageDeliveredTime(LocalDateTime packageDeliveredTime) {
        this.packageDeliveredTime = packageDeliveredTime;
    }
}
