package com.petguardian.views.doctor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.petguardian.controllers.IsDoctorExist;
import com.petguardian.controllers.Pet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DoctorGetStarted {
    private Pet app;
    private GridPane grid;
    public DoctorGetStarted(Pet app){
        System.out.println("constructor of getst");
        this.app=app;
        initialize();
    }

    private void initialize(){
        boolean flag=false;
        try {
            flag=IsDoctorExist.doctorExist();
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!flag)
       // rootpane.getChildren().add(displayCreateDoctorService());
       displayCreateDoctorService();
        System.out.println(flag);

    }

    private void displayCreateDoctorService(){
        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        System.out.println("1+");

        // Firestore ID
        Label firestoreIdLabel = new Label("Firestore ID:");
        GridPane.setConstraints(firestoreIdLabel, 0, 0);
        TextField firestoreIdInput = new TextField();
        GridPane.setConstraints(firestoreIdInput, 1, 0);

        // Name
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 1);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 1);

        // Experience
        Label experienceLabel = new Label("Experience:");
        GridPane.setConstraints(experienceLabel, 0, 2);
        TextField experienceInput = new TextField();
        GridPane.setConstraints(experienceInput, 1, 2);

        // Qualification
        Label qualificationLabel = new Label("Qualification:");
        GridPane.setConstraints(qualificationLabel, 0, 3);
        TextField qualificationInput = new TextField();
        GridPane.setConstraints(qualificationInput, 1, 3);

        // Rating
        Label ratingLabel = new Label("Rating:");
        GridPane.setConstraints(ratingLabel, 0, 4);
        Spinner<Integer> ratingInput = new Spinner<>(0, 5, 0);
        GridPane.setConstraints(ratingInput, 1, 4);

        // Image URL
        Label imgLabel = new Label("Image URL:");
        GridPane.setConstraints(imgLabel, 0, 5);
        TextField imgInput = new TextField();
        GridPane.setConstraints(imgInput, 1, 5);

        // Tags
        Label tagsLabel = new Label("Tags:");
        GridPane.setConstraints(tagsLabel, 0, 6);
        TextField tagsInput = new TextField();
        GridPane.setConstraints(tagsInput, 1, 6);

        // About
        Label aboutLabel = new Label("About:");
        GridPane.setConstraints(aboutLabel, 0, 7);
        TextField aboutInput = new TextField();
        GridPane.setConstraints(aboutInput, 1, 7);

        // Location
        Label locationLabel = new Label("Location:");
        GridPane.setConstraints(locationLabel, 0, 8);
        TextField locationInput = new TextField();
        GridPane.setConstraints(locationInput, 1, 8);

        // Specializes
        Label specializesLabel = new Label("Specializes:");
        GridPane.setConstraints(specializesLabel, 0, 9);
        TextField specializesInput = new TextField();
        GridPane.setConstraints(specializesInput, 1, 9);

        // Contact
        Label contactLabel = new Label("Contact:");
        GridPane.setConstraints(contactLabel, 0, 10);
        TextField contactInput = new TextField();
        GridPane.setConstraints(contactInput, 1, 10);

        // Price
        Label priceLabel = new Label("Price:");
        GridPane.setConstraints(priceLabel, 0, 11);
        TextField priceInput = new TextField();
        GridPane.setConstraints(priceInput, 1, 11);

        // Available
        Label availableLabel = new Label("Available:");
        GridPane.setConstraints(availableLabel, 0, 12);
        ComboBox<Boolean> availableInput = new ComboBox<>(FXCollections.observableArrayList(true, false));
        GridPane.setConstraints(availableInput, 1, 12);

        // Available Days
       /*  Label availableDaysLabel = new Label("Available Days:");
        GridPane.setConstraints(availableDaysLabel, 0, 13);
        DatePicker availableDaysInput = new DatePicker();
        GridPane.setConstraints(availableDaysInput, 1, 13);*/
        HBox dateButtonsBox = new HBox(10); // 10 pixels spacing between buttons
        GridPane.setConstraints(dateButtonsBox, 1, 13); // Adjusted to match your layout

        // Get current date and create buttons for next 5 days
        LocalDateTime currentDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
        //String dateString

        for (int i = 0; i < 5; i++) {
            LocalDateTime date = currentDate.plusDays(i);
            ToggleButton dateButton = new ToggleButton(date.format(formatter));
            dateButton.setShape(new Circle(10)); // Oval shape with a radius of 25
            dateButton.setMinSize(50, 50); // Adjust size as needed
            dateButtonsBox.getChildren().add(dateButton);

            // Add event handler to select the date
            dateButton.setOnAction(event -> {
                if (dateButton.isSelected()) {
                    // Handle selection of the date, e.g., update your logic
                    System.out.println("Selected Date: " + date);
                    dateButton.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Selected style

                    // You can handle further logic here based on selected date
                } else {
                    // Handle deselection if needed
                    dateButton.setStyle("-fx-background-color: red; -fx-text-fill: black;"); // Deselected style

                }
            });
        }

        //grid.getChildren().add(); 

        // Submit Button
        Button submitButton = new Button("Submit");
        GridPane.setConstraints(submitButton, 1, 14);

        submitButton.setOnAction(e -> {
            // Handle form submission, e.g., validation and sending data to backend
            String firestoreId = firestoreIdInput.getText();
            String name = nameInput.getText();
            String experience = experienceInput.getText();
            String qualification = qualificationInput.getText();
            int rating = ratingInput.getValue();
            String img = imgInput.getText();
            String tags = tagsInput.getText();
            String about = aboutInput.getText();
            String location = locationInput.getText();
            String specializes = specializesInput.getText();
            String contact = contactInput.getText();
            String price = priceInput.getText();
            boolean available = availableInput.getValue().booleanValue();
          //  LocalDate availableDays = availableDaysInput.getValue();

            // Print values to the console for debugging
            System.out.println("Firestore ID: " + firestoreId);
            System.out.println("Name: " + name);
            System.out.println("Experience: " + experience);
            System.out.println("Qualification: " + qualification);
            System.out.println("Rating: " + rating);
            System.out.println("Image URL: " + img);
            System.out.println("Tags: " + tags);
            System.out.println("About: " + about);
            System.out.println("Location: " + location);
            System.out.println("Specializes: " + specializes);
            System.out.println("Contact: " + contact);
            System.out.println("Price: " + price);
            System.out.println("Available: " + available);
         //   System.out.println("Available Days: " + availableDays);
        });

        grid.getChildren().addAll(
                firestoreIdLabel, firestoreIdInput,
                nameLabel, nameInput,
                experienceLabel, experienceInput,
                qualificationLabel, qualificationInput,
                ratingLabel, ratingInput,
                imgLabel, imgInput,
                tagsLabel, tagsInput,
                aboutLabel, aboutInput,
                locationLabel, locationInput,
                specializesLabel, specializesInput,
                contactLabel, contactInput,
                priceLabel, priceInput,
                availableLabel, availableInput,dateButtonsBox,
               // availableDaysLabel, availableDaysInput,
                submitButton
        );
    }
    public Pane getView(){
        return grid;
    }
}
