package com.petguardian.views;

import com.petguardian.Model.DoctorModelClass;
import com.petguardian.controllers.DoctorDataFetcher;
import com.petguardian.controllers.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class dummyVetarnary {
    private Pet app;
    private Pane rootpane;
    boolean islogin = true;
    List<DoctorModelClass> doctorList;
    private VBox cardsContainer; // Container for doctor cards

    public dummyVetarnary(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {
        DoctorDataFetcher dataFetcher = new DoctorDataFetcher();
        try {
            doctorList = dataFetcher.fetchDoctorData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");
        cardsContainer = new VBox(20); // VBox to hold doctor cards
        cardsContainer.setLayoutX(150);
        cardsContainer.setLayoutY(490);

        rootpane.getChildren().addAll(backButton(), getTopBox(), cardsContainer);

        // Initially display all doctors
        displayDoctors(doctorList);
    }

    public Pane getView() {
        return rootpane;
    }

    private void displayDoctors(List<DoctorModelClass> doctors) {
        cardsContainer.getChildren().clear();
        for (DoctorModelClass doctor : doctors) {
            HBox card = createDoctorCard(doctor);
            cardsContainer.getChildren().add(card);
        }
    }

    private HBox createDoctorCard(DoctorModelClass doctor) {
        ImageView doctorImageView = new ImageView(new Image("vetarnary/doctor2.png"));
        doctorImageView.setFitWidth(250);
        doctorImageView.setFitHeight(250);
        doctorImageView.setPreserveRatio(true);

        Label doctorNameLabel = new Label(doctor.getName());
        doctorNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label qualificationLabel = new Label(doctor.getQulification());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        Label experienceLabel = new Label(doctor.getExperience() + " Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label(String.valueOf(doctor.getRating()) + "/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
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

        HBox tagsBox = new HBox(10);
        for (String specialization : doctor.getTags()) {
            Label tagLabel = new Label(specialization);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagsBox.getChildren().add(tagLabel);
        }

        Label descriptionLabel = new Label(doctor.getAbout());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);

        VBox leftVBox = new VBox(10, doctorNameLabel, qualificationLabel, experienceLabel, ratingBox, tagsBox,
                descriptionLabel);
        leftVBox.setAlignment(Pos.TOP_LEFT);

        VBox contactVBox = new VBox(20);
        contactVBox.setAlignment(Pos.CENTER);
        contactVBox.setPadding(new Insets(10));

        Label locationLabel = new Label("Location: " + doctor.getLocation());
        locationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

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

        Button callButton = new Button("Call Us : " + doctor.getContact());
        callButton.setStyle("-fx-background-color: #FFA500; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 10; " +
                "-fx-pref-width: 300; -fx-pref-height: 35;");
        Button appointmentButton = new Button("Book Free Appointment");
        appointmentButton.setStyle("-fx-background-color: #FFA500; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 20px; " +
                "-fx-background-radius: 10; " +
                "-fx-pref-width: 300; -fx-pref-height: 35;");

        contactVBox.getChildren().addAll(locationLabel, availabilityLabel, specialistLabel, callButton,
                appointmentButton);

        HBox mainHBox = new HBox(30, doctorImageView, leftVBox, contactVBox);
        mainHBox.setPadding(new Insets(20));
        mainHBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");
        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setEffect(new DropShadow(5, Color.GRAY));
        return mainHBox;
    }

    private Button backButton() {
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setMinSize(130, 40);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");
        backButton.setOnAction(e -> app.navigateToHomeView());
        return backButton;
    }

    BorderPane getTopBox() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLayoutX(20);
        mainLayout.setLayoutY(60);
        mainLayout.setPadding(new Insets(30, 30, 30, 30));

        VBox topSection = new VBox(20);
        topSection.setPadding(new Insets(20));
        topSection.setStyle("-fx-background-color:#002240; -fx-background-radius: 20;");
        topSection.setMinWidth(1200);
        topSection.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Which Doctor to Consult for Piles - Top Piles Doctors in India");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.setPromptText("Select City");
        cityComboBox.getItems().addAll("pune", "mumbai ", "Narhe");

        HBox ratingBox = new HBox(10);
        Label ratingLabel = new Label("Rating");
        ratingLabel.setTextFill(Color.WHITE);
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        ratingBox.getChildren().add(ratingLabel);

        for (int i = 0; i < 4; i++) {
            Image star = new Image("vetarnary/star.png");
            ImageView starView = new ImageView(star);
            starView.setFitHeight(30);
            starView.setFitWidth(30);
            ratingBox.getChildren().add(starView);
        }
        Image halfStar = new Image("vetarnary/halfStar.png");
        ImageView halfStarView = new ImageView(halfStar);
        halfStarView.setFitHeight(30);
        halfStarView.setFitWidth(30);
        ratingBox.getChildren().add(halfStarView);

        HBox statsBox = new HBox(40);
        Label patientsLabel = new Label("2M+ Happy Patients");
        patientsLabel.setTextFill(Color.WHITE);
        patientsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        Label hospitalsLabel = new Label("700+ Hospitals");
        hospitalsLabel.setTextFill(Color.WHITE);
        hospitalsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        Label citiesLabel = new Label("40+ Cities");
        citiesLabel.setTextFill(Color.WHITE);
        citiesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        statsBox.getChildren().addAll(patientsLabel, hospitalsLabel, citiesLabel);

        Button searchButton = new Button("Search");
        searchButton.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        searchButton.setOnAction(e -> {
            String selectedCity = cityComboBox.getValue();
            if (selectedCity != null) {
                List<DoctorModelClass> filteredDoctors = doctorList;
                // List<DoctorModelClass> filteredDoctors = doctorList.stream()
                // .filter(doctor -> doctor.getLocation().equalsIgnoreCase(selectedCity))
                // .toList();
                displayDoctors(filteredDoctors);
            }
        });

        topSection.getChildren().addAll(titleLabel, cityComboBox, ratingBox, statsBox, searchButton);
        mainLayout.setTop(topSection);

        return mainLayout;
    }
}
