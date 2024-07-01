package com.petguardian.Model;

import java.util.Set;

// dr name=xxx
// qulification = MBBS, MS - General Surgery
// experience = 23 Years Experience Overall
// rating  out of 5
// image
// tags = { "Proctology", "Urology", "Laparoscopy", "Vascular", "Aesthetics" };
// discription  ="Dr. Milind Joshi is an expert and experienced General Surgeon, Proctologist, and Laparoscopic Surgeon with an experience of 23 years, and specializes in Proctology, Vascular, Laparoscopy, and Urology. He graduated and obtained his MBBS degree from...");
// Location:Aanvii Hearing Solutions
// Available  yes or no

// specializes :Piles Specialist
// contact no  

public class DoctorModelClass {
    String drName;
    String qulification;
    String experience;
    double rating;
    String image;
    Set tags;
    String discription;
    String location;
    boolean isAvalable;
    String spetilization;
    String contactNo;

    DoctorModelClass(String drName,
            String qulification,
            String experience,
            double rating,
            String image,
            Set tags,
            String discription,
            String location,
            boolean isAvalable,
            String spetilization,
            String contactNo) {
        this.drName = drName;
        this.qulification = qulification;
        this.experience = experience;
        this.rating = rating;
        this.image = image;
        this.tags = tags;
        this.discription = discription;
        this.location = location;
        this.isAvalable = isAvalable;
        this.spetilization = spetilization;
        this.contactNo = contactNo;

    };
}