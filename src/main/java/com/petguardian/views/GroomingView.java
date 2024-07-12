package com.petguardian.views;
import com.petguardian.controllers.Pet;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        ScrollPane rootPane;

        public GroomingView(Pet app) {
                this.app = app;
                initialize();
        }

        public ScrollPane getView() {
                return rootPane;
        }

        public void initialize() {
                rootPane = new ScrollPane();
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

                topSection.getChildren().addAll(dogImageView, discountLabel);

                // Adding animation to topSection
                TranslateTransition topSectionTranslate = new TranslateTransition(Duration.millis(1000), topSection);
                topSectionTranslate.setFromY(-100);
                topSectionTranslate.setToY(0);
                topSectionTranslate.setCycleCount(1);
                topSectionTranslate.setAutoReverse(false);
                topSectionTranslate.play();

                // Adding animation to discountLabel
                ScaleTransition discountLabelScale = new ScaleTransition(Duration.millis(1000), discountLabel);
                discountLabelScale.setFromX(0.8);
                discountLabelScale.setFromY(0.8);
                discountLabelScale.setToX(1.2);
                discountLabelScale.setToY(1.2);
                discountLabelScale.setCycleCount(Animation.INDEFINITE);
                discountLabelScale.setAutoReverse(true);
                discountLabelScale.play();

                ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1.5), dogImageView);
                scaleTransition.setToX(1.1);
                scaleTransition.setToY(1.1);
                scaleTransition.setCycleCount(Animation.INDEFINITE);
                scaleTransition.setAutoReverse(true);

                ParallelTransition parallelTransition = new ParallelTransition(scaleTransition);
                parallelTransition.play();

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

                // Grooming Label
                Label groomingLabel = new Label("Grooming");
                groomingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
                groomingLabel.setTextFill(Color.DARKSLATEGRAY);
                groomingLabel.setAlignment(Pos.CENTER);

                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(2.0);
                dropShadow.setOffsetY(2.0);
                dropShadow.setColor(Color.color(0.4, 0.4, 0.4));
                groomingLabel.setEffect(dropShadow);

                // Add background and padding
                groomingLabel.setStyle("-fx-background-color: #FFA501; -fx-background-radius: 10; -fx-padding: 10;");

                Button backButton = new Button("Back");
                backButton.setLayoutX(20);
                backButton.setLayoutY(20);
                backButton.setMinSize(130, 40);
                backButton.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");
                backButton.setOnAction(e -> app.navigateToHomeView());

                ///

                StackPane backButtonPane = new StackPane(backButton);
                backButtonPane.setAlignment(Pos.TOP_LEFT);
                backButtonPane.setPadding(new Insets(10));

                HBox groomingLabelBox = new HBox(groomingLabel);
                groomingLabelBox.setAlignment(Pos.CENTER);
                groomingLabelBox.setPadding(new Insets(10));

                // Services Grid
                GridPane servicesGrid = new GridPane();
                servicesGrid.setHgap(30);
                servicesGrid.setVgap(20);
                servicesGrid.setPadding(new Insets(25));
                servicesGrid.setAlignment(Pos.CENTER);

                String[] services = { "Bathing & Drying", "Hair Trimming", "Nail Trimming", "Ear Cleaning",
                                "Teeth Brushing", "Gland Expression" };
                String[] imageFiles = { "Grooming/gr2.jpeg", "Grooming/gr3.png", "Grooming/gr4.jpeg",
                                "Grooming/gr5.jpeg", "Grooming/gr6.png", "Grooming/gr7.jpeg" };

                for (int i = 0; i < services.length; i++) {
                        VBox serviceBox = new VBox(10);
                        serviceBox.setPadding(new Insets(10));
                        serviceBox.setStyle(
                                        "-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;");
                        serviceBox.setAlignment(Pos.CENTER);
                        serviceBox.setEffect(new DropShadow());

                        
                        serviceBox.setOnMouseEntered(e -> {
                                serviceBox.setStyle(
                                                "-fx-background-color: #e0e0e0; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;");
                                ScaleTransition st = new ScaleTransition(Duration.millis(200), serviceBox);
                                st.setToX(1.05);
                                st.setToY(1.05);
                                st.play();
                        });
                        serviceBox.setOnMouseExited(e -> {
                                serviceBox.setStyle(
                                                "-fx-background-color: white; -fx-border-color: lightgray; -fx-border-radius: 10; -fx-background-radius: 10;");
                                ScaleTransition st = new ScaleTransition(Duration.millis(200), serviceBox);
                                st.setToX(1.0);
                                st.setToY(1.0);
                                st.play();
                        });

                        Image serviceImage = new Image(imageFiles[i]);
                        ImageView serviceImageView = new ImageView(serviceImage);
                        serviceImageView.setFitWidth(400);
                        serviceImageView.setFitHeight(200);
                        serviceImageView.setStyle("-fx-background-radius: 10;");
                        serviceImageView.setEffect(new DropShadow(10, Color.YELLOW));

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

                mainLayout.getChildren().addAll(backButtonPane, groomingLabelBox, topSection, searchBox, servicesGrid);
                mainLayout.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3,#ffffff);");

                // Add mainLayout to a ScrollPane
                ScrollPane scrollPane = new ScrollPane();
                scrollPane.setContent(mainLayout);
                scrollPane.setFitToWidth(true);
                scrollPane.setPannable(true);
                scrollPane.setStyle("-fx-background: #f0f0f0; -fx-background-color: transparent;");
                rootPane = scrollPane;
        }
}