package com.petguardian.views;

import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginUser {

    private Pet app;
    private Pane rootpane;
    private boolean islogin = true;

    public LoginUser(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {
        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: #FCDBC1");

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER_RIGHT);
        root.setSpacing(30);
        root.setStyle("-fx-padding: 40;");

        ImageView imageView = new ImageView(
                new Image(getClass().getResourceAsStream("/image/login/bg1.png")));
        imageView.setFitWidth(550);
        imageView.setFitHeight(550);

        VBox loginForm = new VBox(40);
        loginForm.setStyle("-fx-padding: 40; -fx-alignment: center-right;");

        Label welcomeLabel = new Label("Welcome Back !!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.BLACK);

        HBox emailFieldContainer = new HBox(10);
        emailFieldContainer.setAlignment(Pos.CENTER_LEFT);
        ImageView emailIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/mail.png")));
        emailIcon.setFitWidth(24);
        emailIcon.setFitHeight(24);
        emailFieldContainer.getChildren().add(emailIcon);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" +
                "    -fx-border-color: transparent transparent black transparent; \n" +
                "    -fx-border-width: 0 0 2px 0; \n" +
                "    -fx-padding: 0 0 2px 0;");
        emailFieldContainer.getChildren().add(emailField);

        HBox passwordFieldContainer = new HBox(10);
        passwordFieldContainer.setAlignment(Pos.CENTER_LEFT);
        ImageView passwordIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/padlock.png")));
        passwordIcon.setFitWidth(24);
        passwordIcon.setFitHeight(24);
        passwordFieldContainer.getChildren().add(passwordIcon);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" +
                "    -fx-border-color: transparent transparent black transparent; \n" +
                "    -fx-border-width: 0 0 2px 0; \n" +
                "    -fx-padding: 0 0 2px 0;");
        passwordFieldContainer.getChildren().add(passwordField);

        HBox userFieldContainer = new HBox(10);
        userFieldContainer.setAlignment(Pos.CENTER_LEFT);
        ImageView userIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/user.png")));
        userIcon.setFitWidth(24);
        userIcon.setFitHeight(24);
        userFieldContainer.getChildren().add(userIcon);

        TextField userField = new TextField();
        userField.setPromptText("@UserName");
        userField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" +
                "    -fx-border-color: transparent transparent black transparent; \n" +
                "    -fx-border-width: 0 0 2px 0; \n" +
                "    -fx-padding: 0 0 2px 0;");
        userFieldContainer.getChildren().add(userField);

        HBox phoneFieldContainer = new HBox(10);
        phoneFieldContainer.setAlignment(Pos.CENTER_LEFT);
        ImageView phoneIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/phone.png")));
        phoneIcon.setFitWidth(24);
        phoneIcon.setFitHeight(24);
        phoneFieldContainer.getChildren().add(phoneIcon);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone No");
        phoneField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" +
                "    -fx-border-color: transparent transparent black transparent; \n" +
                "    -fx-border-width: 0 0 2px 0; \n" +
                "    -fx-padding: 0 0 2px 0;");
        phoneFieldContainer.getChildren().add(phoneField);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: linear-gradient(to right, #FFAA00, #FF6600);\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-padding: 10 20 10 20;\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-border-radius: 20;-fx-pref-width: 300;");

        Label signUpLabel = new Label("Don't have an account? Sign Up");
        signUpLabel.setFont(Font.font("BOLD", 24));
        signUpLabel.setTextFill(Color.BLACK);
        ///
        // Create toggle buttons for role selection
        ToggleGroup roleToggleGroup = new ToggleGroup();

        RadioButton userRadioButton = new RadioButton("User");
        userRadioButton.setToggleGroup(roleToggleGroup);
        userRadioButton.setSelected(true); // Set "User" as the default selection

        RadioButton doctorRadioButton = new RadioButton("Doctor");
        doctorRadioButton.setToggleGroup(roleToggleGroup);

        HBox roleSelection = new HBox(10);
        roleSelection.setAlignment(Pos.CENTER_LEFT);
        roleSelection.getChildren().addAll(userRadioButton, doctorRadioButton);
        ////
        ///

        ///
        // starting la distana login form disnar so this ui
        loginForm.getChildren().addAll(welcomeLabel, emailFieldContainer, passwordFieldContainer,
                loginButton, signUpLabel);

        root.getChildren().addAll(imageView, loginForm);
        root.setMinSize(1800, 1080);
        root.setAlignment(Pos.CENTER);

        rootpane.getChildren().add(root);

        // all functions which are action on click=>

        // login vr click kelyavr kiva sign up vr kelyavr
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String pass = passwordField.getText();
            String userName = userField.getText();
            String phone = phoneField.getText();
            String role = ((RadioButton) roleToggleGroup.getSelectedToggle()).getText();
            submit(email, pass, userName, phone, role);
        });

        // not sign up vr click kelyavr
        signUpLabel.setOnMouseClicked(e -> {
            if (islogin) {
                loginButton.setText("Sign Up");
                loginForm.getChildren().clear();
                loginForm.getChildren().addAll(welcomeLabel, userFieldContainer, emailFieldContainer,
                        phoneFieldContainer, passwordFieldContainer, roleSelection, loginButton, signUpLabel);
                welcomeLabel.setText("Welcome to PetGuardian");
            } else {
                loginButton.setText("Login");
                loginForm.getChildren().clear();
                loginForm.getChildren().addAll(welcomeLabel, emailFieldContainer, passwordFieldContainer,
                        loginButton, signUpLabel);
                welcomeLabel.setText("Welcome Back");
            }
            islogin = !islogin;
            signUpLabel.setText(islogin ? "Don't have an account? Sign Up" : "Already have an account? Login");
        });

    }

    public Pane getView() {
        return rootpane;
    }

    /// submit method
    private void submit(String email, String pass, String userName, String phone, String role) {
        if (!email.contains("@") || !email.contains(".com")) {
            showAlert("Invalid email ");
            return;
        }
        if (pass.length() < 5) {
            showAlert("Weaker password");
            return;
        }

        // /// navigate to SplashScreen
        // app.navigateToSplashView();
        // //
        String result;

        if (islogin) {
            result = MyAuthentication.LoginUser(email, pass);

        } else {
            result = MyAuthentication.RegisterUser(email, pass, userName, phone, role);
        }

        if (result.equalsIgnoreCase("user")) {
            app.navigateToHomeView();
        } else if (result.equalsIgnoreCase("doctor")) {
            app.navigateToGetStartedDoctor();
        } else {
            showAlert(result);

        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Fail ");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
