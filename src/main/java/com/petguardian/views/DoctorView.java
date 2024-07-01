package com.petguardian.views;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.petguardian.controllers.Pet;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class DoctorView {
    private Pet app;
    private GridPane gridPane;

    public DoctorView(Pet app) {
        this.app = app;
        initialize();
    }
    private void initialize(){
          // Create a GridPane layout
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Labels and TextFields
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        Label qualificationLabel = new Label("Qualification:");
        TextField qualificationField = new TextField();
        gridPane.add(qualificationLabel, 0, 1);
        gridPane.add(qualificationField, 1, 1);

        Label experienceLabel = new Label("Experience:");
        TextField experienceField = new TextField();
        gridPane.add(experienceLabel, 0, 2);
        gridPane.add(experienceField, 1, 2);

        Label ratingLabel = new Label("Rating:");
        TextField ratingField = new TextField();
        gridPane.add(ratingLabel, 0, 3);
        gridPane.add(ratingField, 1, 3);

        Label imgLabel = new Label("Image URL:");
        TextField imgField = new TextField();
        gridPane.add(imgLabel, 0, 4);
        gridPane.add(imgField, 1, 4);

        Label tagsLabel = new Label("Tags (comma-separated):");
        TextField tagsField = new TextField();
        gridPane.add(tagsLabel, 0, 5);
        gridPane.add(tagsField, 1, 5);

        Label aboutLabel = new Label("About:");
        TextArea aboutArea = new TextArea();
        aboutArea.setPrefRowCount(4);
        gridPane.add(aboutLabel, 0, 6);
        gridPane.add(aboutArea, 1, 6);

        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();
        gridPane.add(locationLabel, 0, 7);
        gridPane.add(locationField, 1, 7);

        Label availableLabel = new Label("Available:");
        CheckBox availableCheckBox = new CheckBox();
        gridPane.add(availableLabel, 0, 8);
        gridPane.add(availableCheckBox, 1, 8);

        Label specializesLabel = new Label("Specializes:");
        TextField specializesField = new TextField();
        gridPane.add(specializesLabel, 0, 9);
        gridPane.add(specializesField, 1, 9);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            // Retrieve values from fields
            String name = nameField.getText();
            String qualification = qualificationField.getText();
            String experience = experienceField.getText();
            String rating = ratingField.getText();
            String img = imgField.getText();
            String tags = tagsField.getText();
            String about = aboutArea.getText();
            String location = locationField.getText();
            boolean available = availableCheckBox.isSelected();
            String specializes = specializesField.getText();

            // Process the data (e.g., send it to backend or display)
            System.out.println("Name: " + name);
            System.out.println("Qualification: " + qualification);
            System.out.println("Experience: " + experience);
            System.out.println("Rating: " + rating);
            System.out.println("Image URL: " + img);
            System.out.println("Tags: " + tags);
            System.out.println("About: " + about);
            System.out.println("Location: " + location);
            System.out.println("Available: " + available);
            System.out.println("Specializes: " + specializes);

            String jsonInputString = "{\"name\":\"" + name +
            "\",\"qualification\":\"" + qualification +
            "\",\"experience\":\"" + experience +
            "\",\"rating\":\"" + rating +
            "\",\"img\":\"" + img +
            "\",\"tags\":\"" + tags +
            "\",\"about\":\"" + about +
            "\",\"location\":\"" + location +
            "\",\"available\":" + available +
            ",\"specializes\":\"" + specializes +
            "\"}";

            // Send the POST request
            sendDoctorData(jsonInputString);
        });
        gridPane.add(submitButton, 1, 10);
    }

     private void sendDoctorData(String jsonInputString) {
        try {
            
            URL url = new URL("https://pet-api-two.vercel.app/doctorData");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Send the POST request
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Check response code
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Success handling 
                System.out.println("Doctor data sent successfully!");
            } else {
                // Error handling
                System.out.println("Error sending doctor data. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public GridPane getView(){
        return gridPane;
    }
}
