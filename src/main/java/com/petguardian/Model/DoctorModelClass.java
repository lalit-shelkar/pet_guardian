package com.petguardian.Model;

import java.util.List;
import java.util.Set;

public class DoctorModelClass {
    private String firestoreId;
    private String name;
    private String qualification;
    private String experience;
    private double rating;
    private String img;
    private Set<String> tags;
    private String about;
    private String location;
    private String specializes;
    private String contact;
    private double price;
    private boolean available;
    private Set<AvailableDay> availableDays;
    private Set<PatientModelClass> patients;

    public DoctorModelClass(String firestoreId,
                            String name,
                            String qualification,
                            String experience,
                            double rating,
                            String img,
                            Set<String> tags,
                            String about,
                            String location,
                            boolean available,
                            double price,
                            String specializes,
                            String contact,
                            Set<AvailableDay> availableDays,
                            Set<PatientModelClass> patients) {
        this.firestoreId = firestoreId;
        this.name = name;
        this.qualification = qualification;
        this.experience = experience;
        this.rating = rating;
        this.img = img;
        this.tags = tags;
        this.about = about;
        this.location = location;
        this.available = available;
        this.specializes = specializes;
        this.contact = contact;
        this.price = price;
        this.availableDays = availableDays;
        this.patients = patients;
    }

    // Getters
    public String getFirestoreId() {
        return firestoreId;
    }

    public String getName() {
        return name;
    }

    public String getQualification() {
        return qualification;
    }

    public String getExperience() {
        return experience;
    }

    public double getRating() {
        return rating;
    }

    public String getImg() {
        return img;
    }

    public Set<String> getTags() {
        return tags;
    }

    public String getAbout() {
        return about;
    }

    public String getLocation() {
        return location;
    }

    public String getSpecializes() {
        return specializes;
    }

    public String getContact() {
        return contact;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public Set<AvailableDay> getAvailableDays() {
        return availableDays;
    }

    public Set<PatientModelClass> getPatients() {
        return patients;
    }

    // Inner class for AvailableDay
    public static class AvailableDay {
        private String date;
        private List<String> times;

        public AvailableDay(String date, List<String> times) {
            this.date = date;
            this.times = times;
        }

        public String getDate() {
            return date;
        }

        public List<String> getTimes() {
            return times;
        }
    }
}
