package com.petguardian.controllers;

import com.petguardian.views.HomeView;
import com.petguardian.views.DoctorView;

import com.petguardian.views.LoginUser;
import com.petguardian.views.DogFoodView;
import com.petguardian.views.GroomingView;
import com.petguardian.views.ShopView;
import com.petguardian.views.TraningView;
import com.petguardian.views.VetarnaryView;
import com.petguardian.views.common.Navbar;

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
    // dogfood scene
    private DogFoodView dogFoodView;
    private Scene dogFoodScene;
    //
    // for training purpose
    private TraningView traningView;
    private Scene traningScene;
    //
    // for gromming
    private GroomingView groomingView;
    private Scene groomingScene;
    //

    private Navbar navbarView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setX(0);
        primaryStage.setY(0);
        // navbar
        navbarView = new Navbar(this);

        /// home Screen Scene
        homeView = new HomeView(this);
        homeScene = new Scene(homeView.getView(), 1920, 1080);

        /// Login Screen views
        loginView = new LoginUser(this);
        loginScene = new Scene(loginView.getView(), 1920, 1080);

        // vetarnary
        vetarnaryView = new VetarnaryView(this);
        vetarScene = new Scene(vetarnaryView.getView(), 1920, 1080);

        /// shopscreen
        shopView = new ShopView(this);
        shopScene = new Scene(shopView.getView(), 1920, 1080);

        /// shopscreen
        // doctorView =new DoctorView (this);
        // doctorScene = new Scene(doctorView.getView(), 1920, 1080);

        // DogFood View
        dogFoodView = new DogFoodView(this);
        dogFoodScene = new Scene(dogFoodView.getView(), 1920, 1080);

        // traning view
        traningView = new TraningView(this);
        traningScene = new Scene(traningView.getView(), 1920, 1080);
        // grooming screen
        groomingView = new GroomingView(this);
        groomingScene = new Scene(groomingView.getView(), 1920, 1080);
        /// initial screen
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    // navigate to home screen
    public void navigateToHomeView() {
        System.out.println("in home ");
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

    /// navigate to doctor screen
    // public void navigateToDoctorView() {
    // primaryStage.setScene(doctorScene);
    // primaryStage.show();
    // }

    // navigate to dogFodd Screen
    public void navigateToDogFoodView() {

        primaryStage.setScene(dogFoodScene);
        primaryStage.show();
    }

    public void navigateToTraning() {

        primaryStage.setScene(traningScene);
        primaryStage.show();
    }

    // navigate to groooming package

    public void navigateToGrooming() {

        primaryStage.setScene(groomingScene);
        primaryStage.show();
    }

}
