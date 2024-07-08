package com.petguardian;

import java.io.IOException;

import com.petguardian.controllers.Pet;
import com.petguardian.firebase.InitilazationFirebase;
import com.petguardian.util.CloudinaryConnection;

import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        try {
            InitilazationFirebase.initialize();
            CloudinaryConnection.initializeCloudinary();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Application.launch(Pet.class, args);
    }

}