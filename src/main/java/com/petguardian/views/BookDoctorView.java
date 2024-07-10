package com.petguardian.views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.PatientModelClass;
import com.petguardian.Model.DoctorModelClass.AvailableDay;
import com.petguardian.controllers.Pet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BookDoctorView {
    private Pet app;
    private DoctorModelClass doctor;
    private Pane rootPane;
    private boolean isAppointment = false;
    private Button selectedButton = null; // Track the selected button
    private GridPane timeSlotsGrid = new GridPane(); // GridPane for time slots
    private String selectedDate; // Store the selected date

    public BookDoctorView(Pet app, DoctorModelClass doctor) {
        this.app = app;
        this.doctor = doctor;
        initialize();
    }

    private void initialize() {
        rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");
        VBox createPatient = createPatientForm();
        createPatient.setLayoutX(1000);
        createPatient.setLayoutY(200);
        rootPane.getChildren().addAll(createDoctorCard(), createBackButton(), createPatient);
    }

    public Pane getView() {
        return rootPane;
    }

    private HBox createDoctorCard() {
        VBox leftVBox = createLeftVBox();
        VBox contactVBox = createContactVBox();

        HBox mainHBox = new HBox(30, leftVBox, contactVBox);
        mainHBox.setPadding(new Insets(20));
        mainHBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");
        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setEffect(new DropShadow(5, Color.GRAY));
        mainHBox.setLayoutX(50);
        mainHBox.setLayoutY(200);
        return mainHBox;
    }

    private VBox createLeftVBox() {
        StackPane imageContainer = createImageContainer();
        VBox leftVBox = new VBox(10, imageContainer, createNameLabel(), createQualificationLabel(),
                createExperienceLabel(),
                createRatingBox(), createTagsBox(), createDescriptionLabel());
        leftVBox.setAlignment(Pos.TOP_LEFT);
        return leftVBox;
    }

    private StackPane createImageContainer() {
        ImageView doctorImageView = new ImageView(new Image(doctor.getImg()));
        doctorImageView.setFitWidth(350);
        doctorImageView.setFitHeight(350);
        doctorImageView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane(doctorImageView);
        imageContainer.setPrefSize(350, 350);
        imageContainer.setMaxSize(350, 350);
        imageContainer.setMinSize(350, 350);
        return imageContainer;
    }

    private Label createNameLabel() {
        Label qualificationLabel = new Label(doctor.getName());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 26));
        qualificationLabel.setTextFill(Color.BLACK);
        return qualificationLabel;
    }

    private Label createQualificationLabel() {
        Label qualificationLabel = new Label(doctor.getQualification());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 23));
        qualificationLabel.setTextFill(Color.BLACK);
        return qualificationLabel;
    }

    private Label createExperienceLabel() {
        Label experienceLabel = new Label(doctor.getExperience() + " Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        experienceLabel.setTextFill(Color.BLACK);
        return experienceLabel;
    }

    private HBox createRatingBox() {
        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label(String.valueOf(doctor.getRating()) + "/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        ratingLabel.setTextFill(Color.BLACK);
        ratingBox.getChildren().add(ratingLabel);

        for (int i = 0; i < (int) doctor.getRating(); i++) {
            Image star = new Image("vetarnary/star.png");
            ImageView starView = new ImageView(star);
            starView.setFitHeight(20);
            starView.setFitWidth(20);
            ratingBox.getChildren().add(starView);
        }
        if (doctor.getRating() % 1 != 0) {
            Image halfStar = new Image("vetarnary/halfStar.png");
            ImageView halfStarView = new ImageView(halfStar);
            halfStarView.setFitHeight(20);
            halfStarView.setFitWidth(20);
            ratingBox.getChildren().add(halfStarView);
        }
        return ratingBox;
    }

    private HBox createTagsBox() {
        HBox tagsBox = new HBox(10);
        for (String specialization : doctor.getTags()) {
            Label tagLabel = new Label(specialization);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagLabel.setTextFill(Color.BLACK);
            tagsBox.getChildren().add(tagLabel);
        }
        return tagsBox;
    }

    private Label createDescriptionLabel() {
        Label descriptionLabel = new Label(doctor.getAbout());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);
        descriptionLabel.setTextFill(Color.BLACK);
        return descriptionLabel;
    }

    private VBox createContactVBox() {
        VBox contactVBox = new VBox(20);
        contactVBox.setAlignment(Pos.CENTER);
        contactVBox.setPadding(new Insets(10));

        contactVBox.getChildren().addAll(
                createLocationLabel(),
                createAvailabilityLabel(),
                createSpecialistLabel(),
                createPriceLabel(),
                createDaysBox(),
                timeSlotsGrid, // Add time slots grid to the contact VBox
                createCallButton(),
                createAppointmentButton()); // Add patient form here

        return contactVBox;
    }

    private Label createLocationLabel() {
        Label locationLabel = new Label("Location: " + doctor.getLocation());
        locationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        locationLabel.setTextFill(Color.BLACK);
        return locationLabel;
    }

    private Label createAvailabilityLabel() {
        Label availabilityLabel = new Label(doctor.isAvailable() ? "Available" : "Not Available");
        availabilityLabel.setTextFill(doctor.isAvailable() ? Color.GREEN : Color.RED);
        availabilityLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        availabilityLabel.setStyle("-fx-border-color: " + (doctor.isAvailable() ? "green" : "red") + "; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");
        return availabilityLabel;
    }

    private Label createSpecialistLabel() {
        Label specialistLabel = new Label(doctor.getSpecializes());
        specialistLabel.setTextFill(Color.GREEN);
        specialistLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        return specialistLabel;
    }

    private Label createPriceLabel() {
        Label priceLabel = new Label("Price: $" + doctor.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        priceLabel.setTextFill(Color.BLACK);
        return priceLabel;
    }

    private HBox createDaysBox() {
        HBox daysBox = new HBox(10);
        daysBox.setAlignment(Pos.CENTER);

        List<Button> dayCheckBoxes = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM-dd");

        for (int i = 0; i < 5; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(outputFormatter);
            Button dayButton = createDayButton(formattedDate);
            dayCheckBoxes.add(dayButton);
            daysBox.getChildren().add(dayButton);
        }
        return daysBox;
    }

    private Button createDayButton(String formattedDate) {
        Button dayButton = new Button(formattedDate);
        if (doctor.getAvailability().stream().anyMatch(d -> d.getDay().equals(formattedDate))) {
            dayButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        } else {
            dayButton.setDisable(true);
        }
        dayButton.setOnAction(event -> handleButtonClick(dayButton));
        return dayButton;
    }

    private void handleButtonClick(Button dayButton) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        }
        dayButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        selectedButton = dayButton;
        selectedDate = dayButton.getText(); // Update the selected date
        showTimeSlots();
    }

    private void showTimeSlots() {
        timeSlotsGrid.getChildren().clear(); // Clear previous time slots
        if (selectedDate != null) {
            AvailableDay availableDay = doctor.getAvailability().stream()
                    .filter(d -> d.getDay().equals(selectedDate))
                    .findFirst()
                    .orElse(null);

            if (availableDay != null) {
                Set<String> availableTimes = availableDay.getAvailableTimes();
                int row = 0;
                int col = 0;

                for (String time : availableTimes) {
                    Button timeSlotButton = new Button(time);
                    timeSlotButton.setOnAction(event -> handleTimeSlotSelection(timeSlotButton));
                    timeSlotsGrid.add(timeSlotButton, col, row);
                    col++;
                    if (col > 2) {
                        col = 0;
                        row++;
                    }
                }
            }
        }
    }

    private void handleTimeSlotSelection(Button timeSlotButton) {
        if (selectedButton != null) {
            selectedButton.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        }
        timeSlotButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        selectedButton = timeSlotButton;
        String selectedTime = timeSlotButton.getText(); // You can use selectedTime for further logic
    }

    private Button createCallButton() {
        Button callButton = new Button("Call");
        callButton.setStyle("-fx-background-color: orange; -fx-text-fill: white; -fx-font-weight: bold;");
        callButton.setOnAction(event -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Call");
            alert.setHeaderText(null);
            alert.setContentText("Calling " + doctor.getName() + "...");
            alert.showAndWait();
        });
        return callButton;
    }

    private Button createAppointmentButton() {
        Button appointmentButton = new Button("Create Patient Form");
        appointmentButton.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-font-weight: bold;");
        appointmentButton.setOnAction(event -> {
            isAppointment = !isAppointment;
            rootPane.getChildren().clear();
            rootPane.getChildren().addAll(createDoctorCard(), createBackButton(), createPatientForm());
        });
        return appointmentButton;
    }

    private Button createBackButton() {
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(event -> {
            isAppointment = false;
            rootPane.getChildren().clear();
            rootPane.getChildren().addAll(createDoctorCard(), createBackButton());
        });
        return backButton;
    }

    private VBox createPatientForm() {
        Label titleLabel = new Label("Patient Form");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Label breedLabel = new Label("Breed:");
        TextField breedField = new TextField();

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-font-weight: bold;");
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String breed = breedField.getText();

            if (name.isEmpty() || age.isEmpty() || breed.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields.");
                alert.showAndWait();
            } else {
                // PatientModelClass patient = new PatientModelClass(name, Integer.parseInt(age), breed);
                // doctor.addPatient(patient);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Patient information submitted successfully.");
                alert.showAndWait();

                rootPane.getChildren().clear();
                rootPane.getChildren().addAll(createDoctorCard(), createBackButton());
            }
        });

        VBox patientForm = new VBox(10, titleLabel, nameLabel, nameField, ageLabel, ageField, breedLabel, breedField,
                submitButton);
        patientForm.setPadding(new Insets(20));
        patientForm.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");
        return patientForm;
    }
}
