package com.petguardian.views;

import com.petguardian.controllers.Pet;
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
                rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #ff9100, #ffffff);");

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

                // Video Player
                videoPlayer = new WebView();
                videoPlayer.setPrefSize(800, 450);
                webEngine = videoPlayer.getEngine();
                webEngine.load("https://www.youtube.com/embed/GGefmpfvPlI"); // Default video

                VBox videoBox = new VBox(videoPlayer);
                videoBox.setAlignment(Pos.CENTER);
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
                scrollPane.setPrefWidth(300);

                VBox vb = new VBox(20, scrollPane);
                vb.setAlignment(Pos.TOP_CENTER);
                vb.setPadding(new Insets(20));

                root.setRight(vb);

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
                Label titleLabel = new Label("Video");
                titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                // VBox for text
                VBox textBox = new VBox(5, titleLabel);
                textBox.setAlignment(Pos.CENTER_LEFT);

                // HBox for image and text
                HBox videoBox = new HBox(10, videoImageView, textBox);
                videoBox.setAlignment(Pos.CENTER_LEFT);
                videoBox.setPadding(new Insets(10));
                videoBox.setStyle(
                                "-fx-border-color: rgba(128, 0, 128, 0.5); -fx-border-width: 2; -fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;");
                videoBox.setOnMouseEntered(e -> videoBox.setStyle(
                                "-fx-border-color: rgba(0, 128, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 1); -fx-background-radius: 20;"));
                videoBox.setOnMouseExited(e -> videoBox.setStyle(
                                "-fx-border-color: rgba(128, 0, 128, 0.5); -fx-border-width: 4; -fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 20;"));

                // Set video on click
                videoBox.setOnMouseClicked(e -> webEngine.load(videoUrl));

                return videoBox;
        }

        public Pane getView() {
                return rootPane;
        }
}
