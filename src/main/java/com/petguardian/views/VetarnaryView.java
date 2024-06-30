package com.petguardian.views;

import com.petguardian.controllers.Pet;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VetarnaryView {
    private Pet app;
    private Pane rootpane;
    boolean islogin = true;

    public VetarnaryView(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {
        rootpane = new Pane();

        // Main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(20, 20, 20, 20));

        // Top section
        VBox topSection = new VBox(10);
        topSection.setPadding(new Insets(20));
        topSection.setStyle("-fx-background-color: #002240; -fx-background-radius: 10;");

        Label titleLabel = new Label("Which Doctor to Consult for Piles - Top Piles Doctors in India");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.setPromptText("Select City");
        cityComboBox.getItems().addAll("City 1", "City 2", "City 3"); // Example cities

        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label("Rating");
        ratingLabel.setTextFill(Color.WHITE);
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        // Adding stars for rating
        for (int i = 0; i < 4; i++) {
            Image star = new Image("vetarnary/star.png"); // Use your own star image
            ImageView starView = new ImageView(star);
            starView.setFitHeight(20);
            starView.setFitWidth(20);
            ratingBox.getChildren().add(starView);
        }
        Image halfStar = new Image("vetarnary/halfStar.png"); // Use your own half star image
        ImageView halfStarView = new ImageView(halfStar);
        halfStarView.setFitHeight(20);
        halfStarView.setFitWidth(20);
        ratingBox.getChildren().add(halfStarView);

        ratingBox.getChildren().add(0, ratingLabel);

        HBox statsBox = new HBox(30);
        Label patientsLabel = new Label("2M+ Happy Patients");
        patientsLabel.setTextFill(Color.GOLD);
        patientsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label hospitalsLabel = new Label("700+ Hospitals");
        hospitalsLabel.setTextFill(Color.GOLD);
        hospitalsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Label citiesLabel = new Label("40+ Cities");
        citiesLabel.setTextFill(Color.GOLD);
        citiesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        statsBox.getChildren().addAll(patientsLabel, hospitalsLabel, citiesLabel);

        topSection.getChildren().addAll(titleLabel, cityComboBox, ratingBox, statsBox);

        // Right section for the doctor image
        ImageView doctorImage = new ImageView(new Image("vetarnary/doctor.png")); // Replace with actual doctor image
        doctorImage.setFitWidth(200);
        doctorImage.setPreserveRatio(true);

        HBox mainContent = new HBox(20);
        mainContent.getChildren().addAll(topSection, doctorImage);

        mainLayout.setCenter(mainContent);

        rootpane.getChildren().add(mainLayout);
    }

    public Pane getView() {
        return rootpane;
    }
}
