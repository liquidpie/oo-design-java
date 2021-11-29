package com.vivek.vaccine.scheduler.model;

import java.time.LocalDateTime;

public class ProviderAppointment {

    public LocalDateTime appointmentTime;
    public String patientID;

    public ProviderAppointment(LocalDateTime appointmentTime, String patientID) {
        this.appointmentTime = appointmentTime;
        this.patientID = patientID;
    }

    public String toString() {
        return patientID + "-" + appointmentTime.toString();
    }

}
