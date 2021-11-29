package com.vivek.vaccine.scheduler;

import com.vivek.vaccine.scheduler.model.PatientAppointment;
import com.vivek.vaccine.scheduler.model.ProviderAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataStore {

//    public static Map<String, Appointment> APPOINTMENTS = new HashMap<>();
//    public static Map<String, String> PATIENT_APPOINTMENTS = new HashMap<>();
//    public static Map<String, List<String>> PROVIDER_APPOINTMENTS = new HashMap<>();
//
//    public static Map<LocalDate, List<Slot>> SLOTS = new HashMap<>();

    public static Map<String, PatientAppointment> PATIENT_APPTS = new HashMap<>();
    public static Map<String, Map<LocalDate, List<ProviderAppointment>>> PROVIDER_APPTS = new HashMap<>();
    public static Map<LocalDateTime, Set<String>> ALL_APPTS = new HashMap<>();

}
