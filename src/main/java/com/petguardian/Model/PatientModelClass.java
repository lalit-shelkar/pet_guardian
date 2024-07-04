package com.petguardian.Model;

import java.util.Date;

public class PatientModelClass {
    private String patientId;
    private String name;
    private Date appointmentDay;

    public PatientModelClass(String patientId, String name, Date appointmentDay) {
        this.patientId = patientId;
        this.name = name;
        this.appointmentDay = appointmentDay;
    }

    // Getters
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public Date getAppointmentDay() {
        return appointmentDay;
    }

}
