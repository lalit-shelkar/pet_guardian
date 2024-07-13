package com.petguardian.controllers;

import com.petguardian.views.HomeView;
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.ProductModelClass;
import com.petguardian.views.AboutUs;
import com.petguardian.views.BookDoctorView;
import com.petguardian.views.CatFoodView;
import com.petguardian.views.LoginUser;
import com.petguardian.views.ShopCardView;
import com.petguardian.views.DogFoodView;
import com.petguardian.views.GroomingView;
import com.petguardian.views.ShopView;
import com.petguardian.views.SplashScreen;
import com.petguardian.views.TrainingView;
import com.petguardian.views.VetarnaryView;
import com.petguardian.views.common.Navbar;
import com.petguardian.views.doctor.DoctorDashboard;
import com.petguardian.views.doctor.DoctorForm;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pet extends Application {
    static boolean isDoctorExist = false;
    private Stage primaryStage;
    //
    private DoctorGetStarted doctorGetStartedView;
    private Scene doctorGetStartedScene;
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
    private TrainingView traningView;
    private Scene traningScene;
    //
    // for gromming
    private GroomingView groomingView;
    private Scene groomingScene;
    //

    // splash screen
    private SplashScreen splashView;
    private Scene splashScene;

    // shopcartView
    private ShopCardView shopCardView;
    private Scene shopcardScene;

    // cat food
    private CatFoodView catFoodView;
    private Scene catScene;

    ///
    /// about us
    private AboutUs AboutUs;
    private Scene AboutScene;

    private Navbar navbarView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setX(0);
        primaryStage.setY(0);
        // navbar
        navbarView = new Navbar(this);
        ///// shopscreen

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

        ///
        /// about us
        AboutUs = new AboutUs(this);
        AboutScene = new Scene(AboutUs.getView(), 1920, 1080);
        // DogFood View
        dogFoodView = new DogFoodView(this);
        dogFoodScene = new Scene(dogFoodView.getView(), 1920, 1080);

        catFoodView = new CatFoodView(this);
        catScene = new Scene(catFoodView.getView(), 1920, 1080);

        // traning view
        traningView = new TrainingView(this);
        traningScene = new Scene(traningView.getView(), 1920, 1080);
        // grooming screen
        groomingView = new GroomingView(this);
        groomingScene = new Scene(groomingView.getView(), 1920, 1080);

        /// Shopcart view
        shopCardView = new ShopCardView(this);
        shopcardScene = new Scene(shopCardView.getView(), 1920, 1080);

        ///
        /// Splash screen
        splashView = new SplashScreen(this);
        splashScene = new Scene(splashView.getView(), 1920, 1080);

        ///
        /// initial screen
        primaryStage.setScene(splashScene);
        primaryStage.setTitle("Pet Guardian");
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

    // navigate to dogFodd Screen
    public void navigateToDogFoodView() {

        primaryStage.setScene(dogFoodScene);
        primaryStage.show();
    }

    ///
    public void navigateToCatFood() {

        primaryStage.setScene(catScene);
        primaryStage.show();
    }

    ///

    public void navigateToTraning() {

        primaryStage.setScene(traningScene);
        primaryStage.show();
    }

    // navigate to groooming package

    public void navigateToGrooming() {

        primaryStage.setScene(groomingScene);
        primaryStage.show();
    }

    // navigate to splash screen
    public void navigateToSplashView() {

        primaryStage.setScene(splashScene);
        primaryStage.show();
    }

    // navigate if it is doctor
    public void navigateToGetStartedDoctor() {
        doctorGetStartedView = new DoctorGetStarted(this);

    }

    // navigate to SHop cart page

    public void navigateToShopCardView() {

        primaryStage.setScene(shopcardScene);
        primaryStage.show();
    }

    public void navigateToBookDoctorView(DoctorModelClass drObj) {

        BookDoctorView bookDoctorView = new BookDoctorView(this, drObj);
        Scene bookDoctorScene = new Scene(bookDoctorView.getView(), 1980, 1080);
        primaryStage.setScene(bookDoctorScene);
    }

    // navigation to create from
    public void navigationToDoctorForm() {
        DoctorForm doctorForm = new DoctorForm(this);
        primaryStage.setScene(new Scene(doctorForm.getView(), 1920, 1080));

    }

    // doctor dashboard
    public void navigateToDoctorDashboard() {
        DoctorDashboard doctorDashboardView = new DoctorDashboard(this);
        Scene docterScene = new Scene(doctorDashboardView.getView(), 1920, 1080);
        primaryStage.setScene(docterScene);

    }

    ///
    public void navigationToAboutus() {
        primaryStage.setScene(AboutScene);
        primaryStage.show();
    }

    /// ignore this method
    public void addItemToCart(ProductModelClass obj) {
        shopCardView.addProductFromExternal(obj);
    }
}
