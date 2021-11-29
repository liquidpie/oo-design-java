package com.vivek.vaccine.scheduler.model;

public class Appointment {

    public String appointmentId;
    public String patientId;
    public String providerId;
    public String slotId;

    public Appointment(String appointmentId, String patientId, String providerId, String slotId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.providerId = providerId;
        this.slotId = slotId;
    }
}
