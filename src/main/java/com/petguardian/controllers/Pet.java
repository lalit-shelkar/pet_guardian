package com.petguardian.controllers;

import com.petguardian.views.HomeView;
import com.petguardian.views.LoginUser;
import com.petguardian.views.ShopView;
import com.petguardian.views.VetarnaryView;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Pet extends Application {
    private Stage primaryStage;
    // home screen
    private HomeView homeView;
    private Scene homeScene;
    // login screen
    private LoginUser loginView;
    private Scene loginScene;
    // VetarnaryView
    private VetarnaryView vetarnaryView;
    private Scene vetarScene;
    // shopScreen
    private ShopView shopView;
    private Scene shopScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();
        this.primaryStage = primaryStage;
        primaryStage.setX(0);
        primaryStage.setY(0);
        /// home Screen Scene
        homeView = new HomeView(this);
        homeScene = new Scene(homeView.getView(), 1920, 1080);
        /// Login Screen views
        loginView = new LoginUser(this);
        loginScene = new Scene(loginView.getView(), 1920, 1080);
        //
        vetarnaryView = new VetarnaryView(this);
        vetarScene = new Scene(vetarnaryView.getView(), 1920, 1080);

        /// shopscreen
        shopView = new ShopView(this);
        shopScene = new Scene(shopView.getView(), 1920, 1080);
        /// initial screen
        primaryStage.setScene(vetarScene);
        primaryStage.show();
    }

    // navigate to home screen
    public void navigateToHomeView() {
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    // navigation to login screen
    public void navigateToLoginView() {
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    ///
    public void navigateToVetarnaryView() {
        primaryStage.setScene(vetarScene);
        primaryStage.show();
    }

    /// navigate to shop screen(amars )
    public void navigateToShopView() {
        primaryStage.setScene(shopScene);
        primaryStage.show();
    }
}
