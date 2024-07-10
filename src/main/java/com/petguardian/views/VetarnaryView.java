package com.petguardian.views;

import com.petguardian.Model.DoctorModelClass;
import com.petguardian.controllers.DoctorDataFetcher;
import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.util.Duration;

import java.util.List;
import java.util.stream.Collectors;

public class VetarnaryView {
    private Pet app;
    private Pane rootpane;
    boolean islogin = true;
    List<DoctorModelClass> doctorList;
    private VBox cardsContainer; // Container for doctor cards

    public VetarnaryView(Pet app) {
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

        // Create the VBox to hold the doctor cards
        cardsContainer = new VBox(20); // VBox to hold doctor cards
        cardsContainer.setPadding(new Insets(20));
        cardsContainer.setAlignment(Pos.TOP_CENTER);

        rootpane.getChildren().addAll(navBar(), getTopBox(), getScrollpane());

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

    private ScrollPane getScrollpane() {
        // Wrap the VBox in a ScrollPane
        ScrollPane scrollPane = new ScrollPane(cardsContainer);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(450);
        scrollPane.setPrefSize(1700, 600);
        scrollPane.setStyle(
                "-fx-background: rgba(0, 0, 0, 0); " + // Transparent background
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(cardsContainer);
        cardsContainer.setStyle("-fx-background-color: transparent;");
        return scrollPane;
    }

    /// is
    boolean isAppoitment = false;

    private HBox createDoctorCard(DoctorModelClass doctor) {

        ImageView doctorImageView = new ImageView(new Image(doctor.getImg()));
        doctorImageView.setFitWidth(250);
        doctorImageView.setFitHeight(250);
        doctorImageView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane(doctorImageView);
        imageContainer.setPrefSize(250, 250);
        imageContainer.setMaxSize(250, 250);
        imageContainer.setMinSize(250, 250);

        Label doctorNameLabel = new Label(doctor.getName());
        doctorNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        doctorNameLabel.setTextFill(Color.BLACK);

        Label qualificationLabel = new Label(doctor.getQualification());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        qualificationLabel.setTextFill(Color.BLACK);

        Label experienceLabel = new Label(doctor.getExperience() + " Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        experienceLabel.setTextFill(Color.BLACK);

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

        HBox tagsBox = new HBox(10);
        for (String specialization : doctor.getTags()) {
            Label tagLabel = new Label(specialization);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagLabel.setTextFill(Color.BLACK);
            tagsBox.getChildren().add(tagLabel);
        }

        Label descriptionLabel = new Label(doctor.getAbout());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);
        descriptionLabel.setTextFill(Color.BLACK);

        VBox leftVBox = new VBox(10, doctorNameLabel, qualificationLabel, experienceLabel, ratingBox, tagsBox,
                descriptionLabel);
        leftVBox.setAlignment(Pos.TOP_LEFT);

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

        Button callButton = new Button("Call Us : " + doctor.getContact());
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
        appointmentButton.setOnMouseClicked(e -> app.navigateToBookDoctorView(doctor));
        appointmentButton.setOnAction(e -> {
            if (isAppoitment) {
                appointmentButton.setText("Appointment booked");
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
            isAppoitment = !isAppoitment;
        });

        contactVBox.getChildren().addAll(locationLabel, availabilityLabel, specialistLabel, callButton,
                appointmentButton);

        HBox mainHBox = new HBox(30, imageContainer, leftVBox, contactVBox);
        mainHBox.setPadding(new Insets(20));
        mainHBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");
        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setEffect(new DropShadow(5, Color.GRAY));

        // Add hover effect
        mainHBox.setOnMouseEntered(event -> elevateCard(mainHBox));
        mainHBox.setOnMouseExited(event -> resetCardElevation(mainHBox));
        mainHBox.setOnMouseClicked(e -> app.navigateToBookDoctorView(doctor));
        return mainHBox;
    }

    private void elevateCard(HBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1.05);
        st.setToY(1.05);
        st.play();

        card.setEffect(new DropShadow(20, Color.GRAY));
    }

    private void resetCardElevation(HBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();

        card.setEffect(new DropShadow(5, Color.GRAY));
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

    private BorderPane getTopBox() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLayoutX(20);
        mainLayout.setLayoutY(60);
        mainLayout.setPadding(new Insets(30, 30, 30, 30));

        VBox topSection = new VBox(20);
        topSection.setPadding(new Insets(20));
        topSection.setMinWidth(1200);
        topSection.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Doctor to Consult for our pet -Indias Top Pet Wellness Expert");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setWrapText(true);
        titleLabel.setAlignment(Pos.TOP_LEFT);

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.setPromptText("Select City");
        cityComboBox.getItems().addAll(
                "Bangalore", "Pune", "Mumbai", "Narhe", "Shirur");
        cityComboBox.setStyle("-fx-font-size: 20px; -fx-background-radius: 10;");

        HBox ratingBox = new HBox(10);
        Label ratingLabel = new Label("4.5/5");
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

        // Search button
        Button searchButton = new Button("Search");
        searchButton.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        searchButton.setOnAction(e -> {
            String selectedCity = cityComboBox.getValue();
            if (selectedCity != null) {
                List<DoctorModelClass> filteredDoctors = doctorList.stream()
                        .filter(doctor -> doctor.getLocation().trim().equalsIgnoreCase(selectedCity.trim()))
                        .collect(Collectors.toList());
                displayDoctors(filteredDoctors);
            }
        });

        // See All button
        Button seeAllButton = new Button("See All");
        seeAllButton.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        seeAllButton.setOnAction(e -> {
            displayDoctors(doctorList);
        });

        /// refresh button
        Button refreshButton = new Button("Refresh");
        refreshButton.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px 20px; -fx-background-radius: 10;");
        refreshButton.setOnAction(e -> {
            //// feating data gaiin
            DoctorDataFetcher dataFetcher = new DoctorDataFetcher();
            try {
                doctorList = dataFetcher.fetchDoctorData();
                displayDoctors(doctorList);
            } catch (Exception error) {
                error.printStackTrace();
            }
        });

        HBox filterButtonBox = new HBox(30, searchButton, seeAllButton, refreshButton);

        ImageView doctorImageLogo = new ImageView(new Image("vetarnary/doctor.png"));
        doctorImageLogo.setFitHeight(350);
        doctorImageLogo.setFitWidth(400);
        topSection.getChildren().addAll(titleLabel, cityComboBox, ratingBox, statsBox, filterButtonBox);

        // vertical box and imgae
        HBox mainHBox = new HBox(80, topSection, doctorImageLogo);
        mainHBox.setStyle("-fx-background-color:#002240; -fx-background-radius: 20;");
        mainHBox.setMinWidth(1800);
        mainLayout.setTop(mainHBox);

        return mainLayout;
    }

    HBox navBar() {
        Navbar obj = new Navbar(app);
        HBox navBar = new HBox(900, backButton(), obj.navBar());
        navBar.setLayoutX(10);
        navBar.setLayoutY(10);
        return navBar;
    }
}
