package com.vivek.amz.locker.model;

import java.sql.Time;

public class Timing {

    private final Time openTime;
    private final Time closeTime;

    public Timing(String openTime, String closeTime) {
        this.openTime = Time.valueOf(openTime);
        this.closeTime = Time.valueOf(closeTime);
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }
}
