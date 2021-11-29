package com.vivek.vaccine.scheduler.model;

import java.time.LocalDateTime;

public class PatientAppointment {

    public LocalDateTime appointmentTime;
    public String providerID;

    public PatientAppointment(LocalDateTime appointmentTime, String providerID) {
        this.appointmentTime = appointmentTime;
        this.providerID = providerID;
    }

    public String toString() {
        return providerID + "-" + appointmentTime.toString();
    }

}
