package com.vivek.vaccine.scheduler.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Slot {

    public String slotId;
    public LocalDate date;
    public LocalTime time;
    public Set<String> providers;

    public Slot(String slotId, LocalDate date, LocalTime time, String provider) {
        this.slotId = slotId;
        this.date = date;
        this.time = time;
        this.providers = new HashSet<>();
        this.providers.add(provider);
    }
}
