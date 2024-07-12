package com.petguardian.views;

import javafx.util.Duration;
import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HomeView {
    // Object x = MyAuthentication.getUserInfo().get("userName");
    private Pet app;
    private Pane rootpane;
    Label userName = new Label("welcome user");

    public HomeView(Pet app) {

        this.app = app;
        initialize();
    }

    private void initialize() {
        userName.setFont(new Font(30));
        userName.setTextFill(Color.ORANGE);
        userName.setStyle("-fx-font-weight: bold;-fx-font-family: 'Times New Roman';");
        HBox usernameBox = new HBox(userName);
        usernameBox.setMinWidth(1000);
        usernameBox.setAlignment(Pos.TOP_LEFT);

        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 50% 50%, #F5D7C3, #ffffff);");

        ImageView iv = new ImageView(new Image("dog.png"));
        iv.setLayoutX(1116);
        iv.setLayoutY(100);
        Navbar nav = new Navbar(app);
        ImageView logOut = new ImageView(new Image("logout.png"));
        logOut.setFitHeight(30);
        logOut.setFitWidth(30);

        HBox logoutBox = new HBox(logOut);
        logoutBox.setMinWidth(170);
        logoutBox.setMinHeight(50);
        logoutBox.setAlignment(Pos.BOTTOM_RIGHT);

        logOut.setOnMouseClicked(e -> app.navigateToLoginView());

        // navbar
        HBox navBox = new HBox(usernameBox, nav.navBar(), logoutBox);
        rootpane.getChildren().add(iv);
        rootpane.getChildren().add(navBox);
        rootpane.getChildren().add(categoryView());
        rootpane.getChildren().add(categoryLabel());
        rootpane.getChildren().add(heading());

    }

    public Pane getView() {

        return rootpane;
    }

    @SuppressWarnings("static-access")
    private GridPane categoryView() {
        GridPane categoryView = new GridPane();
        categoryView.setLayoutX(100);
        categoryView.setLayoutY(700);
        // categoryView.setPrefHeight(500);
        // categoryView.setPrefWidth(900);
        categoryView.setHgap(60);
        categoryView.setVgap(60);
        categoryView.setPadding(new Insets(10, 10, 10, 10));

        BorderPane b1 = category(new Label("Veitenary"), "category/v.png");
        BorderPane b2 = category(new Label("Grooming"), "category/groom.png");
        BorderPane b3 = category(new Label("Training"), "category/train.png");
        BorderPane b4 = category(new Label("Pet Store"), "category/shop.png");
        categoryView.setConstraints(b1, 0, 0);
        categoryView.setConstraints(b2, 1, 0);
        categoryView.setConstraints(b3, 2, 0);
        categoryView.setConstraints(b4, 3, 0);
        categoryView.getChildren().addAll(b1, b2, b3, b4);

        b1.setOnMouseClicked(e -> {

            app.navigateToVetarnaryView();
        });
        b2.setOnMouseClicked(e -> app.navigateToGrooming());
        b3.setOnMouseClicked(e -> {
            app.navigateToTraning();
        });
        b4.setOnMouseClicked(e -> {
            app.navigateToShopView();
        });
        return categoryView;
    }

    private Label categoryLabel() {
        Label category = new Label("Categories");
        category.setFont(new Font(50));
        category.setTextFill(Color.ORANGE);
        category.setStyle("-fx-font-weight: bold;-fx-font-family: 'Times New Roman';");
        category.setLayoutX(100);
        category.setLayoutY(620);
        return category;
    }

    private BorderPane category(Label name, String url) {

        Label label1 = name;
        label1.setAlignment(Pos.BOTTOM_CENTER);
        ImageView c1 = new ImageView(new Image(url));
        c1.setFitHeight(150);
        c1.setFitWidth(150);
        c1.setPreserveRatio(true);
        c1.setStyle("-fx-background-color: transparent;");

        double radius = 70;
        Circle clip = new Circle(c1.getFitWidth() / 2, c1.getFitHeight() / 2, radius);
        c1.setClip(clip);

        VBox vb1 = new VBox();
        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(5);
        vb1.setPadding(new Insets(0, 0, 10, 0));
        vb1.getChildren().addAll(c1, label1);
        vb1.setMinWidth(170);

        BorderPane borderPane = new BorderPane(vb1);
        borderPane.setBorder(new Border(new BorderStroke(
                Color.ORANGE,
                BorderStrokeStyle.SOLID,
                new CornerRadii(5),
                new BorderWidths(5))));

        borderPane.setOnMouseEntered(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), borderPane);
            scaleTransition.setToX(1.1);
            scaleTransition.setToY(1.1);
            scaleTransition.play();
        });

        borderPane.setOnMouseExited(event -> {
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), borderPane);
            scaleTransition.setToX(1.0);
            scaleTransition.setToY(1.0);
            scaleTransition.play();
        });
        return borderPane;
    }

    private VBox heading() {
        Text heading = new Text("Get Good Health , Food & Accessories \nFor Your Pet");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 54));

        // Create the description text
        Text description = new Text(
                "Petsy is India's favorite online pets shop offering Food,\nAccessories,Toys, Treats,Grooming products,Beds &\nMuch More for Dogs, Cats & Small animals .");
        description.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        description.setLineSpacing(10);

        // Create the button
        Button shopNowButton = new Button("Shop Now");
        shopNowButton.setStyle("-fx-background-radius: 15;-fx-background-color:Orange ; -fx-padding: 10 20 10 20;");

        // Create a VBox to hold the elements
        VBox vbox = new VBox(10); // Spacing between elements is 10
        vbox.setPadding(new Insets(20)); // Padding around the VBox
        vbox.getChildren().addAll(heading, description);
        vbox.setSpacing(50);
        vbox.setLayoutX(100);
        vbox.setLayoutY(200);
        return vbox;
    }
}
