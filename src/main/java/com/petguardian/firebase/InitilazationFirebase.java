package com.petguardian.firebase;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class InitilazationFirebase {

        public static void initialize() throws IOException {
                FileInputStream serviceAccount = new FileInputStream(
<<<<<<< HEAD
                                "C:\\Users\\Dell\\Desktop\\JAVA-PRAC\\JavaFX\\petgaurdian\\src\\main\\resources\\fir-javafx-47969-firebase-adminsdk-44ci7-d6afe1530a.json");
=======
                                "C:\\Users\\abhis\\Desktop\\JAVAFX\\project\\pet_guardian\\src\\main\\resources\\fir-javafx-47969-firebase-adminsdk-44ci7-d6afe1530a.json");
>>>>>>> 8bbe36aca9be104e171571fec6fca2e36ec07050

                FirebaseOptions options = new FirebaseOptions.Builder()
                                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                                .build();

                FirebaseApp.initializeApp(options);
                System.out.println("initilize completed");
        }
}
