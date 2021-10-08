package com.vivek.amz.locker.model;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

public class LocationTiming {

    private Map<DayOfWeek, Timing> timingMap = new HashMap<>();

    public Map<DayOfWeek, Timing> getTimingMap() {
        return timingMap;
    }

    public void setTimingMap(Map<DayOfWeek, Timing> timingMap) {
        this.timingMap = timingMap;
    }
}
