package com.petguardian.views;

import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.geometry.Pos;

public class LoginUser {

    private Pet app;
    private Pane rootpane;
    boolean islogin = true;

    public LoginUser(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {
        
        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: #FCDBC1");

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER_RIGHT); // Set alignment to center-right
        root.setSpacing(30); // Add some spacing between the image and the login form
        root.setStyle("-fx-padding: 40;");

        // Create the left side with the image
        ImageView imageView = new ImageView(
                new Image(getClass().getResourceAsStream("/image/login/bg1.png")));
        imageView.setFitWidth(550);
        imageView.setFitHeight(550);

        // Create the right side with login form
        VBox loginForm = new VBox(40);

        loginForm.setStyle("-fx-padding: 40; -fx-alignment: center-right;");

        Label welcomeLabel = new Label("Welcome Back !!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        welcomeLabel.setTextFill(Color.BLACK);

        HBox emailFieldContainer = new HBox(10);
        emailFieldContainer.setAlignment(Pos.CENTER_LEFT);

        ImageView emailIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/mail.png")));
        emailIcon.setFitWidth(24); // Set the desired width for the email icon
        emailIcon.setFitHeight(24); // Set the desired height for the email icon
        emailFieldContainer.getChildren().add(emailIcon);

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" + //
                "    -fx-border-color: transparent transparent black transparent; \n" + //
                "    -fx-border-width: 0 0 2px 0; \n" + //
                "    -fx-padding: 0 0 2px 0; \n" + //
                "");
        emailFieldContainer.getChildren().add(emailField);
        /////
        // password fild
        HBox passwordFieldContainer = new HBox(10);
        passwordFieldContainer.setAlignment(Pos.CENTER_LEFT);
        // image
        ImageView passwordIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/padlock.png")));
        passwordIcon.setFitWidth(24); // Set the desired width for the password icon
        passwordIcon.setFitHeight(24); // Set the desired height for the password icon
        passwordFieldContainer.getChildren().add(passwordIcon);
        ///////////
        /////
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" + //
                "    -fx-border-color: transparent transparent black transparent; \n" + //
                "    -fx-border-width: 0 0 2px 0; \n" + //
                "    -fx-padding: 0 0 2px 0; \n" + //
                "");
        passwordFieldContainer.getChildren().add(passwordField);
        /////
        // Username TextField

        HBox userFieldContainer = new HBox(10);
        userFieldContainer.setAlignment(Pos.CENTER_LEFT);

        ImageView userIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/user.png")));
        userIcon.setFitWidth(24); // Set the desired width for the email icon
        userIcon.setFitHeight(24); // Set the desired height for the email icon
        userFieldContainer.getChildren().add(userIcon);

        TextField userField = new TextField();
        userField.setPromptText("@UserName");
        userField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" + //
                "    -fx-border-color: transparent transparent black transparent; \n" + //
                "    -fx-border-width: 0 0 2px 0; \n" + //
                "    -fx-padding: 0 0 2px 0; \n" + //
                "");
        userFieldContainer.getChildren().add(userField);
        //
        // phone number
        HBox phoneFieldContainer = new HBox(10);
        phoneFieldContainer.setAlignment(Pos.CENTER_LEFT);

        ImageView phoneIcon = new ImageView(new Image(getClass().getResourceAsStream("/image/login/phone.png")));
        phoneIcon.setFitWidth(24); // Set the desired width for the email icon
        phoneIcon.setFitHeight(24); // Set the desired height for the email icon
        phoneFieldContainer.getChildren().add(phoneIcon);
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone No");
        phoneField.setStyle("-fx-pref-width: 300; -fx-padding: 10;-fx-background-color: transparent; \n" + //
                "    -fx-border-color: transparent transparent black transparent; \n" + //
                "    -fx-border-width: 0 0 2px 0; \n" + //
                "    -fx-padding: 0 0 2px 0; \n" + //
                "");
        phoneFieldContainer.getChildren().add(phoneField);
        ////
        /// login button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: linear-gradient(to right, #FFAA00, #FF6600);\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-padding: 10 20 10 20;\n" +
                "    -fx-font-size: 20px;\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-background-radius: 20;\n" +
                "    -fx-border-radius: 20;-fx-pref-width: 300;");

        // sign up lable
        Label signUpLabel = new Label("Don't have an account? Sign Up");
        signUpLabel.setFont(Font.font("BOLD", 24));
        signUpLabel.setTextFill(Color.BLACK);
        signUpLabel.setOnMouseClicked(e -> {

            if (islogin) {
                loginButton.setText("signUp");
                loginForm.getChildren().clear();

                loginForm.getChildren().addAll(welcomeLabel, userFieldContainer, emailFieldContainer,
                        phoneFieldContainer,
                        passwordFieldContainer, loginButton,
                        signUpLabel);
                welcomeLabel.setText("Welcome to PetGuardian");

            } else {
                loginButton.setText("Login");
                loginForm.getChildren().clear();
                loginForm.getChildren().addAll(welcomeLabel, emailFieldContainer, passwordFieldContainer, loginButton,
                        signUpLabel);
                welcomeLabel.setText("Welcome Back");
            }
            islogin = !islogin;
            signUpLabel.setText((islogin) ? "Don't have an account? Sign Up" : "Already have an account");

        });

        loginForm.getChildren().addAll(welcomeLabel, emailFieldContainer, passwordFieldContainer, loginButton,
                signUpLabel);

        // Add imageView and loginForm to root
        root.getChildren().addAll(imageView, loginForm);
        root.setMinSize(1800, 1080);
        root.setAlignment(Pos.CENTER);

        // On login button click
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String pass = passwordField.getText();
            String userName = userField.getText();
            String phone = phoneField.getText();
            submit(email, pass, userName, phone);

        });

        rootpane.getChildren().add(root);
    }

    public Pane getView() {
        return rootpane;
    }
    //

    ////
    // methos ==>
    // submit fiorm
    private void submit(String email, String pass, String userName, String phone) {
        // cheking email is valid or not
        if (!email.contains("@") || !email.contains(".com")) {
            showAlert("Invalid email ");
            return;
        }
        // password is small then return
        //
        if (pass.length() < 5) {
            showAlert("Weaker password");
            return;
        }

        String result = "";

        if (islogin) {
            result = MyAuthentication.LoginUser(email, pass);
        } else {
            result = MyAuthentication.RegisterUser(email, pass, userName, phone);
        }

        if (result.equals("success")) {
            app.navigateToHomeView();
        } else {
            showAlert(result);
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Fail ");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
