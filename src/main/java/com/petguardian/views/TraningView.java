package com.petguardian.views;

import com.petguardian.controllers.Pet;

import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.util.Duration;

public class TraningView {
        Pet app;
        Pane rootPane;

        public TraningView(Pet app) {
                this.app = app;
                initialize();
        }

        BorderPane root = new BorderPane();

        public void initialize() {
                rootPane = new Pane(); // Initialize rootPane here
                rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #ff9100, #ffffff);");

                // Side Image
                Image sideImage = new Image("Traning/bg1.png");
                ImageView sideImageView = new ImageView(sideImage);
                sideImageView.setFitWidth(2000);
                sideImageView.setFitHeight(700);
                sideImageView.setPreserveRatio(true);

                // Background

                // Back button
                Button backButton = new Button("Back");
                backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                backButton.setPadding(new Insets(10, 20, 10, 20));
                backButton.setOnMouseClicked(e -> app.navigateToHomeView());
                backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 40;");
                backButton.setOnMouseEntered(e -> backButton
                                .setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 25;"));
                backButton.setOnMouseExited(e -> backButton
                                .setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 25;"));

                HBox topBox = new HBox(backButton);
                topBox.setAlignment(Pos.TOP_LEFT);
                topBox.setPadding(new Insets(10));

                // Title
                Label titleLabel = new Label("Training");
                titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 80));
                titleLabel.setTextFill(Color.WHITE);
                titleLabel.setAlignment(Pos.CENTER);

                // Adding shadow effect to the title
                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(4);
                dropShadow.setOffsetY(4);
                dropShadow.setColor(Color.GRAY);
                titleLabel.setEffect(dropShadow);

                VBox titleBox = new VBox(titleLabel);
                titleBox.setAlignment(Pos.CENTER);
                titleBox.setPadding(new Insets(10));

                VBox topContainer = new VBox(topBox, titleBox);
                topContainer.setAlignment(Pos.CENTER);

                root.setTop(topContainer);

                // List of Courses
                VBox coursesBox = new VBox(30);
                coursesBox.setPadding(new Insets(20));
                coursesBox.setAlignment(Pos.TOP_CENTER);
                coursesBox.setPrefHeight(500);
                coursesBox.setPrefWidth(700);
                coursesBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-background-radius: 10;");

                // Example course items
                coursesBox.getChildren()
                                .add(createCourseItem("Traning/photo1.jpg", "Obedience Courses", "By John Smith",
                                                "4.9 (335)"));
                coursesBox.getChildren().add(createCourseItem("Traning/photo2.jpg", "Specialty Classes & Workshops",
                                "By Duke Fuzzington", "5.0 (500)"));
                coursesBox.getChildren().add(createCourseItem("Traning/photo3.jpg", "Puppy Kindergarten and Playgroups",
                                "By Sir Fluffington", "5.0 (500)"));
                coursesBox.getChildren().add(createCourseItem("Traning/photo4.jpg", "Canine Good Citizen Test",
                                "By Baron Fuzzypaws", "4.8 (220)"));
                coursesBox.getChildren()
                                .add(createCourseItem("Traning/photo5.jpg", "Therapy Dogs", "By Duke Fuzzington",
                                                "5.0 (500)"));

                VBox vb = new VBox(20, coursesBox);
                vb.setAlignment(Pos.TOP_CENTER);
                vb.setPadding(new Insets(20));

                root.setRight(vb);

                // Left Box for the side image
                VBox leftBox = new VBox(sideImageView);
                leftBox.setAlignment(Pos.CENTER_LEFT);
                leftBox.setPrefWidth(1000);
                leftBox.setPadding(new Insets(40));
                root.setLeft(leftBox);

                // Scene
                rootPane.getChildren().add(root);
        }

        private HBox createCourseItem(String imagePath, String title, String author, String rating) {
                // Course Image
                Image courseImage = new Image(imagePath);
                ImageView courseImageView = new ImageView(courseImage);
                courseImageView.setFitWidth(200);
                courseImageView.setFitHeight(100);
                courseImageView.setStyle("-fx-background-color:YELLOW");

                // Labels
                Label titleLabe2 = new Label(title);
                titleLabe2.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                Label authorLabel = new Label(author);
                authorLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

                Label ratingLabel = new Label("⭐ " + rating);
                ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));

                // Adding inner shadow effect to the text
                InnerShadow innerShadow = new InnerShadow();
                innerShadow.setOffsetX(1);
                innerShadow.setOffsetY(1);
                innerShadow.setColor(Color.LIGHTGRAY);
                titleLabe2.setEffect(innerShadow);
                authorLabel.setEffect(innerShadow);
                ratingLabel.setEffect(innerShadow);

                // VBox for text
                VBox textBox = new VBox(5, titleLabe2, authorLabel, ratingLabel);
                textBox.setAlignment(Pos.CENTER_LEFT);

                // HBox for image and text
                HBox courseBox = new HBox(10, courseImageView, textBox);
                courseBox.setAlignment(Pos.CENTER_LEFT);
                courseBox.setPadding(new Insets(10));
                courseBox.setStyle(
                                "-fx-border-color: rgba(128, 0, 128, 0.5); -fx-border-width: 2; -fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;");
                courseBox.setOnMouseEntered(e -> courseBox.setStyle(
                                "-fx-border-color: rgba(0, 128, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 1); -fx-background-radius: 20;"));
                courseBox.setOnMouseExited(e -> courseBox.setStyle(
                                "-fx-border-color: rgba(128, 0, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;"));

                // Add hover animation
                courseBox.setOnMouseEntered(e -> {
                        courseBox.setStyle(
                                        "-fx-border-color: rgba(0, 128, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 1); -fx-background-radius: 20;");
                        ScaleTransition st = new ScaleTransition(Duration.millis(200), courseBox);
                        st.setToX(1.05);
                        st.setToY(1.05);
                        st.play();
                });
                courseBox.setOnMouseExited(e -> {
                        courseBox.setStyle(
                                        "-fx-border-color: rgba(128, 0, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;");
                        ScaleTransition st = new ScaleTransition(Duration.millis(200), courseBox);
                        st.setToX(1);
                        st.setToY(1);
                        st.play();
                });

                return courseBox;
        }

        public Pane getView() {
                return rootPane;
        }
}
