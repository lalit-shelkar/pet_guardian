package com.petguardian.views;

import com.petguardian.Model.DoctorModelClass;
import com.petguardian.controllers.Pet;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    public BookDoctorView(Pet app, DoctorModelClass doctor) {
        this.app = app;
        this.doctor = doctor;
        initialize();
    }

    private void initialize() {
        rootPane = new Pane();
        rootPane.getChildren().addAll(doctorCard());
    }

    public Pane getView() {
        return rootPane;
    }

    private HBox doctorCard() {
        // Doctor's Image
        ImageView doctorImageView = new ImageView(new Image(doctor.getImg()));
        doctorImageView.setFitWidth(150);
        doctorImageView.setFitHeight(150);
        doctorImageView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane(doctorImageView);
        imageContainer.setPrefSize(150, 150);
        imageContainer.setMaxSize(150, 150);
        imageContainer.setMinSize(150, 150);

        // Doctor's Information
        Label doctorNameLabel = new Label(doctor.getName());
        doctorNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        doctorNameLabel.setTextFill(Color.BLACK);

        Label qualificationLabel = new Label(doctor.getQualification());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        qualificationLabel.setTextFill(Color.BLACK);

        Label experienceLabel = new Label(doctor.getExperience() + " Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        experienceLabel.setTextFill(Color.BLACK);

        // Doctor's Rating
        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label(String.valueOf(doctor.getRating()) + "/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        ratingBox.getChildren().add(ratingLabel);
        ratingLabel.setTextFill(Color.BLACK);

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

        // Doctor's Tags
        HBox tagsBox = new HBox(10);
        for (String specialization : doctor.getTags()) {
            Label tagLabel = new Label(specialization);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagLabel.setTextFill(Color.BLACK);
            tagsBox.getChildren().add(tagLabel);
        }

        // Doctor's Description
        Label descriptionLabel = new Label(doctor.getAbout());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);
        descriptionLabel.setTextFill(Color.BLACK);

        VBox leftVBox = new VBox(10, doctorNameLabel, qualificationLabel, experienceLabel, ratingBox, tagsBox,
                descriptionLabel);
        leftVBox.setAlignment(Pos.TOP_LEFT);

        // Doctor's Contact Information
        VBox contactVBox = new VBox(20);
        contactVBox.setAlignment(Pos.CENTER);
        contactVBox.setPadding(new Insets(10));

        Label locationLabel = new Label("Location: " + doctor.getLocation());
        locationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        locationLabel.setTextFill(Color.BLACK);

        Label availabilityLabel = new Label(doctor.isAvailable() ? "Available" : "Not Available");
        availabilityLabel.setTextFill(doctor.isAvailable() ? Color.GREEN : Color.RED);
        availabilityLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        availabilityLabel.setStyle("-fx-border-color: " + (doctor.isAvailable() ? "green" : "red") + "; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");

        Label specialistLabel = new Label(doctor.getSpecializes());
        specialistLabel.setTextFill(Color.GREEN);
        specialistLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Label priceLabel = new Label("Price: $" + doctor.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        priceLabel.setTextFill(Color.BLACK);

        Label availableDaysLabel = new Label("Available Days: " + String.join(", ", doctor.getAvailableDays()));
        availableDaysLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        availableDaysLabel.setTextFill(Color.BLACK);

        Button callButton = new Button("Call Us: " + doctor.getContact());
        callButton.setStyle("-fx-background-color: white; " +
                "-fx-text-fill: #FFA500; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 20px;  " +
                "-fx-background-radius: 20; " +
                "-fx-pref-width: 300; " +
                "-fx-pref-height: 35; " +
                "-fx-border-color:  #FFA500; " +
                "-fx-border-width: 2; ");

        Button appointmentButton = new Button("Book Free Appointment");
        appointmentButton.setStyle("-fx-background-color: #FFA500; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 10; " +
                "-fx-pref-width: 300; -fx-pref-height: 35;");
        appointmentButton.setOnAction(e -> {
            if (isAppointment) {
                appointmentButton.setText("Appointment Booked");
                appointmentButton.setStyle("-fx-background-color: green; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 20px; " +
                        "-fx-background-radius: 10; " +
                        "-fx-pref-width: 300; -fx-pref-height: 35;");
            } else {
                appointmentButton.setText("Book Free Appointment");
                appointmentButton.setStyle("-fx-background-color: #FFA500; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 20px; " +
                        "-fx-background-radius: 10; " +
                        "-fx-pref-width: 300; -fx-pref-height: 35;");
            }
            isAppointment = !isAppointment;
        });

        contactVBox.getChildren().addAll(locationLabel, availabilityLabel, specialistLabel, priceLabel,
                availableDaysLabel, callButton, appointmentButton);

        HBox mainHBox = new HBox(30, imageContainer, leftVBox, contactVBox);
        mainHBox.setPadding(new Insets(20));
        mainHBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");
        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setEffect(new DropShadow(5, Color.GRAY));

        return mainHBox;
    }
}
