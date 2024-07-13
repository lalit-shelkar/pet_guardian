package com.petguardian.views;

import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;

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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Duration;
import javafx.scene.control.ScrollPane;

public class TrainingView {
        Pet app;
        Pane rootPane;
        WebView videoPlayer;
        WebEngine webEngine;
        VBox videoList;

        public TrainingView(Pet app) {
                this.app = app;
                initialize();
        }

        BorderPane root = new BorderPane();

        public void initialize() {
                rootPane = new Pane();
                rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");

                HBox appBar = appBar();

                // Back button
                /*
                 * Button backButton = new Button("Back");
                 * backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                 * backButton.setPadding(new Insets(10, 20, 10, 20));
                 * backButton.setOnMouseClicked(e -> app.navigateToHomeView());
                 * backButton.
                 * setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 40;"
                 * );
                 * backButton.setOnMouseEntered(e -> backButton
                 * .setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 25;"
                 * ));
                 * backButton.setOnMouseExited(e -> backButton
                 * .setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-background-radius: 25;"
                 * ));
                 * 
                 * HBox topBox = new HBox(backButton);
                 * topBox.setAlignment(Pos.TOP_LEFT);
                 * topBox.setPadding(new Insets(10));
                 * 
                 */

                // Title
                Label titleLabel = new Label("Training");
                titleLabel.setStyle("-fx-font-weight: bold;");
                titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 60));
                // titleLabel.setTextFill(Color.WHITE);
                titleLabel.setAlignment(Pos.CENTER);

                // Adding shadow effect to the title
                DropShadow dropShadow = new DropShadow();
                dropShadow.setOffsetX(4);
                dropShadow.setOffsetY(4);
                dropShadow.setColor(Color.GRAY);
                titleLabel.setEffect(dropShadow);

                VBox titleBox = new VBox(appBar, titleLabel);
                titleBox.setAlignment(Pos.CENTER);
                titleBox.setPadding(new Insets(10));

                VBox topContainer = new VBox(titleBox);
                topContainer.setAlignment(Pos.CENTER);

                root.setTop(topContainer);

                // Video Player
                videoPlayer = new WebView();
                videoPlayer.setPrefSize(800, 450);
                videoPlayer.setLayoutY(500);
                webEngine = videoPlayer.getEngine();
                webEngine.load("https://www.youtube.com/embed/GGefmpfvPlI"); // Default video

                VBox videoBox = new VBox(videoPlayer);
                // videoBox.setAlignment(Pos.CENTER);
                videoBox.setPadding(new Insets(20));

                root.setCenter(videoBox);

                // Video List
                videoList = new VBox(20);
                videoList.setPadding(new Insets(20));
                videoList.setAlignment(Pos.TOP_CENTER);

                // Example video items
                videoList.getChildren().add(
                                createVideoItem("Traning/photo1.jpg", "https://www.youtube.com/embed/GGefmpfvPlI"));
                videoList.getChildren().add(
                                createVideoItem("Traning/photo2.jpg", "https://www.youtube.com/embed/dQw4w9WgXcQ"));
                videoList.getChildren().add(
                                createVideoItem("Traning/photo3.jpg", "https://www.youtube.com/embed/9bZkp7q19f0"));
                videoList.getChildren().add(
                                createVideoItem("Traning/photo4.jpg", "https://www.youtube.com/embed/3JZ_D3ELwOQ"));
                videoList.getChildren().add(
                                createVideoItem("Traning/photo5.jpg", "https://www.youtube.com/embed/L_jWHffIx5E"));

                // Make the video list scrollable
                ScrollPane scrollPane = new ScrollPane(videoList);
                scrollPane.setFitToWidth(true);
                scrollPane.setPrefWidth(900);
                scrollPane.setStyle("-fx-background-color: transparent;");

                root.setRight(scrollPane);

                // Scene
                rootPane.getChildren().add(root);
        }

        private HBox createVideoItem(String imagePath, String videoUrl) {
                // Video Thumbnail
                Image videoImage = new Image(imagePath);
                ImageView videoImageView = new ImageView(videoImage);
                videoImageView.setFitWidth(300); // Larger size
                videoImageView.setFitHeight(200); // Larger size
                videoImageView.setStyle("-fx-background-color:YELLOW");

                // Labels
                Label titleLabel = new Label(
                                "The EASIEST Way to Get Your Dog to Understand You! (How to teach your dog words and phrases now!)");
                titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
                titleLabel.setLineSpacing(10);
                // titleLabel.setPadding(new Insets(600, 20, 0, 100));
                titleLabel.setMaxWidth(500);
                titleLabel.setWrapText(true);
                // titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                // VBox for text
                VBox textBox = new VBox(5, titleLabel);
                textBox.setAlignment(Pos.CENTER_LEFT);

                // HBox for image and text
                HBox videoBox = new HBox(10, videoImageView, textBox);
                // videoBox.setAlignment(Pos.CENTER_LEFT);
                videoBox.setPadding(new Insets(20));
                videoBox.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: black;" + // Set the border color
                                                "-fx-border-style: solid;" + // Set the border style (solid, dashed,
                                                                             // dotted, etc.)
                                                "-fx-border-radius: 20px;" + // Set the border radius
                                                "-fx-background-radius: 20px;");

                // videoBox.setOnMouseEntered(event -> elevateCard(videoBox));
                // videoBox.setOnMouseExited(event -> resetCardElevation(videoBox));

                // Set video on click
                videoBox.setOnMouseClicked(e -> webEngine.load(videoUrl));

                return videoBox;
        }

        private HBox appBar() {
                Navbar obj = new Navbar(app);

                HBox appbar = new HBox(900);
                appbar.getChildren().addAll(backButton(), obj.navBar());
                return appbar;
        }

        private Button backButton() {

                Button backButton = new Button("Back");
                backButton.setLayoutX(20);
                backButton.setLayoutY(20);
                backButton.setMinSize(130, 40);
                backButton.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");
                backButton.setOnMouseClicked(e -> {
                        System.out.println("in back button in food ");
                        app.navigateToHomeView();
                });
                return backButton;
        }

        /*
         * private void elevateCard(VBox card) {
         * ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
         * st.setToX(1.05);
         * st.setToY(1.05);
         * st.play();
         * DropShadow shadow = new DropShadow();
         * shadow.setColor(Color.GRAY);
         * shadow.setRadius(10);
         * card.setEffect(shadow);
         * }
         * 
         * private void resetCardElevation(VBox card) {
         * ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
         * st.setToX(1);
         * st.setToY(1);
         * st.play();
         * card.setEffect(null);
         * }
         */

        public Pane getView() {
                return rootPane;
        }
}