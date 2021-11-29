package com.vivek.vaccine.scheduler;

import com.vivek.vaccine.scheduler.model.PatientAppointment;
import com.vivek.vaccine.scheduler.model.ProviderAppointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class VaccineScheduler {

    /**
     * Reserve a patient appointment with this provider and appointment time
     * @param patientID A unique patient ID
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void scheduleAppointment(String patientID, String providerID, LocalDateTime appointmentTime)
    {
        if (DataStore.PATIENT_APPTS.containsKey(patientID)) {
            throw new IllegalStateException("An appointment already exists");
        }
        Set<String> availableProviderAppts = DataStore.ALL_APPTS.get(appointmentTime);
        if (availableProviderAppts != null && availableProviderAppts.contains(providerID)) {
            PatientAppointment patientAppointment = new PatientAppointment(appointmentTime, providerID);
            DataStore.PATIENT_APPTS.put(patientID, patientAppointment);
            availableProviderAppts.remove(providerID);
            LocalDate day = LocalDate.of(appointmentTime.getYear(), appointmentTime.getMonth(), appointmentTime.getDayOfMonth());
            Map<LocalDate, List<ProviderAppointment>> providerAppts = DataStore.PROVIDER_APPTS.get(providerID);
            if (providerAppts == null) {
                providerAppts = new HashMap<>();
                providerAppts.put(day, new ArrayList<>());
            }
            providerAppts.get(day).add(new ProviderAppointment(appointmentTime, patientID));


            DataStore.PROVIDER_APPTS.put(providerID, providerAppts);
        }

    }

    /**
     * Cancel an existing appointment for a patient. If this patient has no
     * appointment, do nothing.
     * @param patientID A unique patient ID
     */
    public void cancelAppointment(String patientID)
    {
        PatientAppointment appt = DataStore.PATIENT_APPTS.get(patientID);
        if (appt != null) {
            DataStore.PATIENT_APPTS.remove(patientID);
            Set<String> providers = DataStore.ALL_APPTS.get(appt.appointmentTime);
            providers.add(appt.providerID);
        }
    }

    /**
     * Get this patient's appointment information
     * @param patientID A unique patient ID
     * @return A PatientAppointment with this patient's appointment information, or
     *         null if this patient has no appointment reserved
     */
    public PatientAppointment getPatientAppointment(String patientID)
    {
        return DataStore.PATIENT_APPTS.get(patientID);
    }

    /**
     * Get open appointments on this day for patients to browse
     * @param day A calendar date
     * @return A mapping of appointment time to list of provider IDs
     *         indicating which providers have available appointments for each
     *         appointment time on this day
     */
    public AbstractMap<LocalDateTime, AbstractCollection<String>> getAvailableAppointments(LocalDate day)
    {
        return null;
    }

    /**
     * Make a new appointment with this provider available
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void addAppointment(String providerID, LocalDateTime appointmentTime)
    {
        Set<String> providers = DataStore.ALL_APPTS.getOrDefault(appointmentTime, new HashSet<>());
        providers.add(providerID);
        DataStore.ALL_APPTS.put(appointmentTime, providers);
    }

    /**
     * Remove an available appointment for a provider at this time. If this
     * provider has no appointment at this time, do nothing.
     * @param providerID A unique provider ID
     * @param appointmentTime Appointment date and time
     */
    public void removeAppointment(String providerID, LocalDateTime appointmentTime) {
        Set<String> providers = DataStore.ALL_APPTS.get(appointmentTime);
        if (providers != null) {
            providers.remove(providerID);
        }
    }

    /**
     * Get this provider's patient schedule for this day
     * @param providerID A unique provider ID
     * @param day A calendar date
     * @return A list of ProviderAppointment objects (containing appointment
     *         times and patient IDs), sorted by appointment time, which
     *         represents the patient schedule for this provider on this day
     */
    public List<ProviderAppointment> getProviderSchedule(String providerID, LocalDate day)
    {
        Map<LocalDate, List<ProviderAppointment>> appts = DataStore.PROVIDER_APPTS.get(providerID);
        return appts != null ? appts.get(day) : new ArrayList<>();
    }

}
