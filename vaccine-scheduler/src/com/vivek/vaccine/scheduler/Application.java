package com.vivek.vaccine.scheduler;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {
        VaccineScheduler vs = new VaccineScheduler();
        String providerId1 = "p1";
        String providerId2 = "p2";
        String patientId1 = "u1";
        String patientId2 = "u2";

        LocalDateTime appt1 = LocalDateTime.of(2021, 11, 24, 10, 0);
        LocalDateTime appt2 = LocalDateTime.of(2021, 11, 24, 10, 30);

        // Add appt
        vs.addAppointment(providerId1, appt1);
        vs.addAppointment(providerId2, appt1);
        vs.addAppointment(providerId1, appt2);

        // schedule appt
        vs.scheduleAppointment(patientId1, providerId1, appt1);
        vs.scheduleAppointment(patientId2, providerId1, appt2);

        // Get Patient appt
        System.out.println(vs.getPatientAppointment(patientId1));
        System.out.println(vs.getPatientAppointment(patientId2));

        // Cancel appointment
        vs.cancelAppointment(patientId1);

        // Get provider schedule
        System.out.println(vs.getProviderSchedule(providerId1, LocalDate.of(2021, 11, 24)));
        System.out.println(vs.getProviderSchedule(providerId2, LocalDate.of(2021, 11, 24)));
    }

}
