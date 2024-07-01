package com.petguardian.Model;

import java.util.Set;

public class DoctorModelClass {
    private String name;
    private String qulification;
    private String experience;
    private double rating;
    private String img;
    private Set<String> tags;
    private String about;
    private String location;
    private boolean available;
    private String specializes;
    private String contact;

    public DoctorModelClass(String name,
            String qulification,
            String experience,
            double rating,
            String img,
            Set<String> tags,
            String about,
            String location,
            boolean available,
            String specializes, String contact) {
        this.name = name;
        this.qulification = qulification;
        this.experience = experience;
        this.rating = rating;
        this.img = img;
        this.tags = tags;
        this.about = about;
        this.location = location;
        this.available = available;
        this.specializes = specializes;
        this.contact = contact;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getQulification() {
        return qulification;
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

    public boolean isAvailable() {
        return available;
    }

    public String getSpecializes() {
        return specializes;
    }

    public String getContact() {
        return contact;
    }
}
