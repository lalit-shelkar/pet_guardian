package com.petguardian.controllers;

import java.util.Map;

import com.petguardian.firebase.MyAuthentication;
import com.petguardian.views.doctor.DoctorDashboard;

import javafx.scene.Scene;

public class DoctorGetStarted {
    private Pet app;

    public DoctorGetStarted(Pet app) {

        this.app = app;
        initialize();
    }

    private void initialize() {
        boolean flag = false;
        try {

            Map<String, Object> user = MyAuthentication.getUserInfo();
            flag = IsDoctorExist.doctorExist(user.get("uid").toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!flag) {
            // doctor doen not fill form
            app.navigationToDoctorForm();

        } else {
            /// doctor already fill form
            /// navigate to the doctor DashBord
            app.navigateToDoctorDashboard();
        }
    }
}
