package com.petguardian.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class InitilazationFirebase {

        public static void initialize() throws IOException {
                FileInputStream serviceAccount = new FileInputStream(
                                "C:\\Users\\Dell\\Desktop\\JAVA-PRAC\\JavaFX\\petgaurdian\\src\\main\\resources\\fir-javafx-47969-firebase-adminsdk-44ci7-d6afe1530a.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                                .build();

                FirebaseApp.initializeApp(options);

                System.out.println("initilize completed");
        }
}
