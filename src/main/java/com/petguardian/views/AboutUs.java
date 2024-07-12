package com.petguardian.views;

import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AboutUs {

    Pet app;
    Pane rootPane;

    public AboutUs(Pet app) {
        this.app = app;
        initialize();
    }

    void initialize() {
        rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");

        Image image = new Image("/About/About.png");
        ImageView img = new ImageView(image);
        img.setFitWidth(500);
        img.setPreserveRatio(true);
        img.setLayoutX(500);

        VBox vb1 = new VBox();
        vb1.getChildren().add(img);
        vb1.setPadding(new Insets(0, 20, 0, 500));
        vb1.setPrefHeight(500);
        vb1.setPrefWidth(300);
        // vb1.setAlignment(Pos.TOP_CENTER);
        // vb1.setStyle("-fx-background-color: linear-gradient(to
        // bottom,rgba(251,247,230,1),rgba(215,176,177,1))");
        // vb1.setLayoutX(500);

        Label about = new Label(
                "At Pet Care, we're dedicated to enhancing the lives of pets and their owners through innovative technology and compassionate care. Our mission is to provide a seamless experience that promotes the well-being of your beloved companions, ensuring they receive the love and attention they deserve.Founded with a passion for pets, PET CARE was born out of a desire to simplify pet care routines while maintaining the highest standards of service and convenience. Whether you're a seasoned pet parent or new to the joys of pet ownership, our app is designed to support you every step of the way.");
        // about.setStyle("-fx-font-weight: bold;");
        about.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        about.setLineSpacing(10);
        about.setPadding(new Insets(600, 20, 0, 100));
        about.setMaxWidth(1000);
        about.setWrapText(true);

        Label title = new Label("'' PET-CARE ''");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, 60));
        title.setLineSpacing(10);
        title.setPadding(new Insets(200, 20, 0, 1100));
        // title.setMaxWidth(1000);
        // title.setWrapText(true);

        Image function = new Image("/About/function.png");
        ImageView fun = new ImageView(function);
        // fun.setFitWidth(500);
        fun.setPreserveRatio(true);
        // fun.setLayoutX(1100);

        VBox features = new VBox();
        features.getChildren().add(fun);
        features.setPadding(new Insets(500, 20, 0, 1000));
        features.setPrefHeight(600);
        features.setPrefWidth(600);

        Group gr = new Group(vb1, about, features, title, appBar());

        rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");
        rootPane.getChildren().add(gr);

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

    public Pane getView() {
        return rootPane;
    }
}