package com.petguardian.views.common;

import java.util.Map;

import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class Navbar {
    private Pet app;

    public Navbar(Pet app) {
        this.app = app;
        navBar();
    }

    public HBox navBar() {
        HBox hb = new HBox();
        // hb.setPrefWidth(getScreenWidth());
        // hb.setPrefHeight(80);
        hb.setSpacing(65);
        hb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-padding: 10;");
        Label home = new Label("Home");
        Label service = new Label("Service");
        Label shop = new Label("Shop");
        Label cart = new Label("Cart");
        Label profile = new Label("Profile");
        Label notification = new Label("About");

        home.setFont(new Font(20));
        service.setFont(new Font(20));
        shop.setFont(new Font(20));
        cart.setFont(new Font(20));
        profile.setFont(new Font(20));
        notification.setFont(new Font(20));

        home.setOnMouseClicked(e -> app.navigateToHomeView());
        shop.setOnMouseClicked(e -> app.navigateToShopView());
        cart.setOnMouseClicked(e -> app.navigateToShopCardView());
        service.setOnMouseClicked(e -> app.navigateToVetarnaryView());
        notification.setOnMouseClicked(e -> app.navigationToAboutus());

        hb.getChildren().addAll(home, service, shop, cart, profile, notification);
        return hb;

    }

    public static double getScreenWidth() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        return visualBounds.getWidth();
    }

    public static double getScreenHeight() {
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        return visualBounds.getHeight();
    }

    public static HBox searchBar() {
        HBox serch = new HBox();
        serch.setPrefWidth(700);
        serch.setPrefHeight(10);
        serch.setStyle("-fx-border-color: black; " +
                "-fx-border-radius: 25%; " +
                "-fx-background-radius: 25%;-fx-padding: 0 0 0 10;");
        serch.setSpacing(50);

        ImageView serchIcon = new ImageView(new Image("search2.png"));
        serchIcon.setFitHeight(50);
        serchIcon.setFitWidth(50);

        serch.setAlignment(Pos.CENTER_LEFT);
        TextField serchField = new TextField();
        serchField.setPromptText("Search..");
        serchField.setPrefWidth(550);
        serchField.setPrefHeight(45);
        serchField.setStyle("-fx-border-color:transparent;" + "-fx-border-radius: 25%;"
                + "-fx-background-color:transparent;" + "-fx-prompt-text-fill: grey; -fx-text-fill: black;");
        serch.getChildren().addAll(serchIcon, serchField);
        return serch;
    }
}
