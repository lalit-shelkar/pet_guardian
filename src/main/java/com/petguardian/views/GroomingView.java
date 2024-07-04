package com.petguardian.views;

import com.petguardian.controllers.Pet;

import javafx.animation.FadeTransition;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.util.Duration;

public class GroomingView {
    Pet app;
    Pane rootPane;

    public GroomingView(Pet app) {
        this.app = app;
        initialize();
    }

    public Pane getView() {
        return rootPane;
    }

    public void initialize() {
        rootPane = new Pane();
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f0f0f0;");

        // Top Section
        HBox topSection = new HBox(20);
        topSection.setPadding(new Insets(15));
        topSection.setStyle("-fx-background-color: #FFA500; -fx-background-radius: 10;");
        topSection.setAlignment(Pos.CENTER_LEFT);

        Label discountLabel = new Label("60% OFF\nOn hair & spa treatment");
        discountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        discountLabel.setTextFill(Color.WHITE);

        Image dogImage = new Image("Grooming/gr1.jpeg");
        ImageView dogImageView = new ImageView(dogImage);
        dogImageView.setFitWidth(100);
        dogImageView.setFitHeight(60);

        topSection.getChildren().addAll(discountLabel, dogImageView);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setMaxWidth(300);
        searchField.setPadding(new Insets(10));
        searchField.setStyle(
                "-fx-background-radius: 5; -fx-border-radius: 5; -fx-border-color: lightgray; -fx-border-width: 1;");
        searchField.setAlignment(Pos.CENTER);

        HBox searchBox = new HBox(searchField);
        searchBox.setAlignment(Pos.CENTER);
        searchBox.setPadding(new Insets(10));

        // Services Grid
        GridPane servicesGrid = new GridPane();
        servicesGrid.setHgap(20);
        servicesGrid.setVgap(20);
        servicesGrid.setPadding(new Insets(20));
        servicesGrid.setAlignment(Pos.CENTER);

        String[] services = { "Bathing & Drying", "Hair Trimming", "Nail Trimming", "Ear Cleaning", "Teeth Brushing",
                "Gland Expression", "Gland Expression" };
        String[] imageFiles = { "Grooming/gr2.jpeg", "Grooming/gr3.png", "Grooming/gr4.jpeg",
                "Grooming/gr5.jpeg", "Grooming/gr6.png", "Grooming/gr7.jpeg", "Grooming/gr7.jpeg" };

        for (int i = 0; i < services.length; i++) {
            VBox serviceBox = new VBox(10);
            serviceBox.setPadding(new Insets(10));
            serviceBox.setStyle(
                    "-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;");
            serviceBox.setAlignment(Pos.CENTER);
            serviceBox.setEffect(new DropShadow());

            // Add hover effect
            serviceBox.setOnMouseEntered(e -> serviceBox.setStyle(
                    "-fx-background-color: #e0e0e0; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;"));
            serviceBox.setOnMouseExited(e -> serviceBox.setStyle(
                    "-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;"));

            Image serviceImage = new Image(imageFiles[i]);

            ImageView serviceImageView = new ImageView(serviceImage);
            serviceImageView.setFitWidth(400);
            serviceImageView.setFitHeight(200);
            serviceImageView.setStyle("-fx-background-radius: 10;");

            Label serviceLabel = new Label(services[i]);
            serviceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

            serviceBox.getChildren().addAll(serviceImageView, serviceLabel);
            servicesGrid.add(serviceBox, i % 2, i / 2);

            // Add fade-in animation
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), serviceBox);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        }

        mainLayout.getChildren().addAll(topSection, searchBox, servicesGrid);

        // Add mainLayout to a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background: #f0f0f0; -fx-background-color: transparent;");

        rootPane.getChildren().add(scrollPane);
    }
}