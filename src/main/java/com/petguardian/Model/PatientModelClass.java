package com.petguardian.Model;

import java.util.Date;

public class PatientModelClass {
    private String _id;
    private String patientId;
    private String name;
    private String contact;
    private String petName;
    private String petType;
    private int petAge;
    private String symptoms;
    private String appointmentDay;
    private String appointmentTime;
    private String status;
    private String createdAt;




    public PatientModelClass(String _id,String patientId, String name,String contact,String petName,String petType ,int petAge,String symptoms ,String appointmentDay,  String appointmentTime,String status,String createdAt) {
        this._id=_id;
        this.patientId = patientId;
        this.name = name;
        this.contact = contact;
        this.petName = petName;
        this.petType = petType;
        this.petAge=petAge;
        this.symptoms=symptoms;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.createdAt=createdAt;
    }

    public String get_Id() {
        return _id;
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

    public String getPetName() {
        return petName;
    }
    public String getPetType() {
        return petType;
    }

    public int getPetAge() {
        return petAge;
    }

    public String getsymptoms() {
        return symptoms;
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
    public void setStatus(String status) {
        this.status=status;
    }
    public String getCreatedAt() {
        return createdAt;
    }


    public void setStatus(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }
   

}
