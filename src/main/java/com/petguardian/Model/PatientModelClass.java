package com.petguardian.Model;

import java.util.Date;

public class PatientModelClass {
    private String patientId;
    private String name;
    private String contact;
    private String petType;
    private String appointmentDay;
    private String appointmentTime;
    private String status;



    public PatientModelClass(String patientId, String name,String contact, String petType, String appointmentDay,  String appointmentTime,String status) {
        this.patientId = patientId;
        this.name = name;
        this.contact = contact;
        this.petType = petType;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }



    public String getPatientId() {
        return patientId;
    }



    public String getName() {
        return name;
    }



    public String getContact() {
        return contact;
    }



    public String getPetType() {
        return petType;
    }



    public String getAppointmentDay() {
        return appointmentDay;
    }



    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getStatus() {
        return status;
    }
   

}
