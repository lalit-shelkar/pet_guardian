package com.petguardian.views;

import com.petguardian.controllers.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
       
        //main pane set
        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: linear-gradient(from  50% 50% to 0% 0% , #F5D7C3, #ffffff);");

        

        rootpane.getChildren().add(getTopBox());
    }

    public Pane getView() {
        return rootpane;
    }

    ///
    ///
    BorderPane getTopBox() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(30, 30, 30, 30));

        // Top section
        VBox topSection = new VBox(20);
        topSection.setPadding(new Insets(20));
        topSection.setStyle("-fx-background-color:#002240  ; -fx-background-radius: 20;");
        topSection.setMinWidth(1200);
        topSection.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Which Doctor to Consult for Piles - Top Piles Doctors in India");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.setPromptText("Select City");
        cityComboBox.getItems().addAll("pune", "mumbai ", "Narhe"); // Example cities

        HBox ratingBox = new HBox(10);
        Label ratingLabel = new Label("Rating");
        ratingLabel.setTextFill(Color.WHITE);
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Adding stars for rating
        for (int i = 0; i < 4; i++) {
            Image star = new Image("vetarnary/star.png"); // Use your own star image
            ImageView starView = new ImageView(star);
            starView.setFitHeight(30);
            starView.setFitWidth(30);
            ratingBox.getChildren().add(starView);
        }
        Image halfStar = new Image("vetarnary/halfStar.png"); // Use your own half star image
        ImageView halfStarView = new ImageView(halfStar);
        halfStarView.setFitHeight(30);
        halfStarView.setFitWidth(30);
        ratingBox.getChildren().add(halfStarView);

        ratingBox.getChildren().add(0, ratingLabel);

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

        topSection.getChildren().addAll(titleLabel, cityComboBox, ratingBox, statsBox);

        // Right section for the doctor image
        ImageView doctorImage = new ImageView(new Image("vetarnary/doctor.png")); // Replace with actual doctor image
        doctorImage.setFitWidth(400);
        doctorImage.setPreserveRatio(true);

        HBox mainContent = new HBox(30);
        mainContent.getChildren().addAll(topSection, doctorImage);

        mainLayout.setCenter(mainContent);

        return mainLayout;
    }
}
