package com.petguardian.views;

import com.petguardian.controllers.Pet;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SplashScreen {
    Pet app;
    Pane rootpane;

    public SplashScreen(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {
        rootpane = new Pane();

        // Create the logo
        Image logoImage = new Image("Splash/back.png"); // Update with the path to your logo image
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setFitWidth(200); // Adjust the size as needed
        logoImageView.setFitHeight(200); // Adjust the size as needed

        // Create the title
        Text title = new Text("PetGuardian");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        // Create the subtitle
        Text subtitle = new Text("Your Pets' Lifelong Protector");
        
        subtitle.setFill(Color.WHITE);
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 24));

        // Arrange elements in a vertical box
        VBox vbox = new VBox(30, logoImageView, title, subtitle);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(1920, 1080);

        // Set the background color
        StackPane root = new StackPane(vbox);
        root.setStyle("-fx-background-color: #FF8C42;"); // Orange background color

        rootpane.getChildren().add(root);
        rootpane.setPrefSize(1920, 1080);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(2), // Duration of the splash screen
                event -> {

                    app.navigateToLoginView();

                }));
        timeline.play();

    }

    public Pane getView() {
        return rootpane;
    }
}
