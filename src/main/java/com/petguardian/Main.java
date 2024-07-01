package com.petguardian;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.petguardian.controllers.Pet;

import javafx.application.Application;

public class Main {
    static {
        try {
            FileInputStream serviceAccount = new FileInputStream(
                    "fir.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://javafx-project1-83368-default-rtdb.asia-southeast1.firebasedatabase.app")
                    .build();
            FirebaseApp.initializeApp(options);

        } catch (IOException e) {
            System.out.println("error");
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Application.launch(Pet.class, "Hello world!");
    }
}