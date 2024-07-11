package com.petguardian.views;

import com.petguardian.Model.ProductModelClass;
import com.petguardian.controllers.Pet;
import com.petguardian.controllers.ProductDataFetch;
import com.petguardian.views.common.Navbar;

import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.List;

public class DogFoodView {
    Label notificationLabel;
    private Pet app;
    private Pane rootpane;
    boolean isDog = true;
    ProductDataFetch productobj = new ProductDataFetch();
    List<ProductModelClass> productList = productobj.productList;

    public DogFoodView(Pet app) throws Exception {
        this.app = app;
        this.rootpane = new Pane(); // Initialize the rootpane
        initialize();
    }

    private void initialize() throws Exception {

        Label title = new Label("Trending Products");
        title.setTextFill(Color.BLACK);
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(new Font(35));
        title.setLayoutX(800);
        title.setLayoutY(100);

        /// dog label
        Label dogLabel = new Label("Dog    |");
        dogLabel.setTextFill(Color.ORANGE);
        dogLabel.setStyle("-fx-font-weight: bold;");
        dogLabel.setFont(new Font(30));
        ////
        // //cat Label
        Label catlabel = new Label("Cat     |");
        catlabel.setTextFill(Color.BLACK);
        catlabel.setStyle("-fx-font-weight: bold;");
        catlabel.setFont(new Font(30));
        ///
        // rabit label
        Label rabitLabel = new Label("   Rabbit  |");
        rabitLabel.setTextFill(Color.BLACK);
        rabitLabel.setStyle("-fx-font-weight: bold;");
        rabitLabel.setFont(new Font(30));
        //
        /// Birds label
        Label birdLabel = new Label("   Birds");
        birdLabel.setTextFill(Color.BLACK);
        birdLabel.setStyle("-fx-font-weight: bold;");
        birdLabel.setFont(new Font(30));

        ///
        Label viewAll = new Label("View All ->");
        viewAll.setTextFill(Color.BLACK);
        // viewAll.setStyle("-fx-font-weight: bold;");
        viewAll.setFont(new Font(25));

        HBox horizmontal = new HBox(40, dogLabel, catlabel, rabitLabel, birdLabel);
        // category box contain dog and cat category
        HBox categoryBox = new HBox(1100, horizmontal, viewAll);
        categoryBox.setPadding(new Insets(200, 0, 0, 100));

        ///

        HBox hb = new HBox(50);
        hb.setPrefHeight(600);
        hb.setPrefWidth(1900);
        hb.setLayoutY(300);
        hb.setPadding(new Insets(40, 0, 50, 70));

        // render box bythe fault
        for (ProductModelClass product : productList) {
            if (product.getCategory().equalsIgnoreCase("dogfood")) {
                hb.getChildren().add(foodCard(product));
            }
        }

        /// when we click the Dog label
        dogLabel.setOnMouseClicked(e -> {
            hb.getChildren().clear();
            for (ProductModelClass product : productList) {
                if (product.getCategory().equalsIgnoreCase("dogfood")) {
                    hb.getChildren().add(foodCard(product));
                }
            }
            dogLabel.setTextFill(Color.ORANGE);
            rabitLabel.setTextFill(Color.BLACK);
            birdLabel.setTextFill(Color.BLACK);
        });
        /// when we click cat
        catlabel.setOnMouseClicked(e -> {
            app.navigateToCatFood();
        });

        /// when we click on rabit
        rabitLabel.setOnMouseClicked(e -> {
            hb.getChildren().clear();
            for (ProductModelClass product : productList) {
                if (product.getCategory().equalsIgnoreCase("rabitfood")) {
                    hb.getChildren().add(foodCard(product));
                }
            }
            dogLabel.setTextFill(Color.BLACK);
            rabitLabel.setTextFill(Color.ORANGE);
            birdLabel.setTextFill(Color.BLACK);
            if (hb.getChildren().isEmpty()) {
                Label empty = new Label("No product Avalable 🙁");
                empty.setTextFill(Color.BLACK);

                empty.setFont(new Font(30));

                hb.getChildren().add(empty);
            }
        });

        /// on click Bird then

        birdLabel.setOnMouseClicked(e -> {
            hb.getChildren().clear();
            for (ProductModelClass product : productList) {
                if (product.getCategory().equalsIgnoreCase("birdfood")) {
                    hb.getChildren().add(foodCard(product));
                }
            }
            dogLabel.setTextFill(Color.BLACK);
            rabitLabel.setTextFill(Color.BLACK);
            birdLabel.setTextFill(Color.ORANGE);
            if (hb.getChildren().isEmpty()) {
                Label empty = new Label("No product Avalable 🙁");
                empty.setTextFill(Color.BLACK);

                empty.setFont(new Font(30));
                empty.setAlignment(Pos.CENTER);
                hb.getChildren().add(empty);
            }
        });

        /// when we click view all
        viewAll.setOnMouseClicked(e -> {
            hb.getChildren().clear();
            for (ProductModelClass product : productList) {

                hb.getChildren().add(foodCard(product));

            }
            dogLabel.setTextFill(Color.BLACK);
            rabitLabel.setTextFill(Color.BLACK);
            birdLabel.setTextFill(Color.BLACK);
        });
        ////
        ScrollPane scrollPane = new ScrollPane(hb);
        scrollPane.setLayoutX(50);
        scrollPane.setLayoutY(300);
        scrollPane.setPrefSize(1850, 600);
        scrollPane.setStyle(
                "-fx-background: rgba(0, 0, 0, 0); " + // Transparent background
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setContent(hb);
        hb.setStyle("-fx-background-color: transparent;");

        ///

        Group gr = new Group(title, categoryBox, appBar(), scrollPane);

        StackPane root = new StackPane(gr);
        // rootpane.setStyle("-fx-background-color: rgba(251,247,230,1);");
        rootpane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");

        rootpane.getChildren().add(root);
    }

    public Pane getView() {
        return rootpane;
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
        backButton.setOnMouseClicked(e -> app.navigateToHomeView());
        return backButton;
    }

    private VBox foodCard(ProductModelClass prObj) {
        Image image = new Image(prObj.getImg());
        ImageView imageview = new ImageView(image);

        imageview.setFitWidth(300);
        imageview.setFitHeight(300);
        imageview.setPreserveRatio(true);

        VBox box3 = new VBox();
        box3.getChildren().add(imageview);
        box3.setPadding(new Insets(20, 20, 0, 20));
        box3.setPrefHeight(300);
        box3.setPrefWidth(300);
        box3.setStyle("-fx-background-color: linear-gradient(to bottom,rgba(251,247,230,1),rgba(215,176,177,1))");
        box3.setAlignment(Pos.CENTER);

        // product name
        Label prName = new Label(prObj.getName());
        prName.setTextFill(Color.BLACK);
        prName.setStyle("-fx-font-weight: bold;");
        prName.setFont(new Font(25));
        prName.setPadding(new Insets(15, 0, 0, 0));

        // rating box
        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label(String.valueOf(prObj.getRating()) + "/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        ratingLabel.setTextFill(Color.BLACK);

        for (int i = 0; i < (int) prObj.getRating(); i++) {
            Image star = new Image("vetarnary/star.png");
            ImageView starView = new ImageView(star);
            starView.setFitHeight(20);
            starView.setFitWidth(20);
            ratingBox.getChildren().add(starView);
        }
        if (prObj.getRating() % 1 != 0) {
            Image halfStar = new Image("vetarnary/halfStar.png");
            ImageView halfStarView = new ImageView(halfStar);
            halfStarView.setFitHeight(20);
            halfStarView.setFitWidth(20);
            ratingBox.getChildren().add(halfStarView);
        }

        Label price = new Label("Price : " + prObj.getPrice() + "          ");
        price.setTextFill(Color.BLACK);
        // price.setStyle("-fx-font-weight: bold;");
        price.setFont(new Font(20));

        HBox ratingContainer = new HBox(10);
        ratingContainer.setAlignment(Pos.CENTER);
        ratingContainer.setPrefWidth(300);
        ratingContainer.getChildren().addAll(price, ratingLabel, ratingBox);

        // add to cart button
        Button boxButton3 = new Button("Add to Cart");
        boxButton3.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");
        boxButton3.setFont(new Font(15));
        boxButton3.setFocusTraversable(false);
        ////////
        boxButton3.setOnMouseClicked(e -> {

            app.addItemToCart(prObj);
            showNotification("product Added Sucssesfully");

        });

        VBox vb3 = new VBox(10, box3, prName, ratingContainer, boxButton3);
        vb3.setPadding(new Insets(20, 20, 0, 20));
        vb3.setPrefHeight(300);
        vb3.setPrefWidth(400);
        vb3.setStyle(
                "-fx-background-color:white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 5, 0, 0, 0);");

        // Adding hover effect for elevation
        vb3.setOnMouseEntered(event -> elevateCard(vb3));
        vb3.setOnMouseExited(event -> resetCardElevation(vb3));

        return vb3;
    }

    private void elevateCard(VBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1.05);
        st.setToY(1.05);
        st.play();

        card.setEffect(new DropShadow(20, Color.GRAY));
    }

    private void resetCardElevation(VBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1.0);
        st.setToY(1.0);
        st.play();

        card.setEffect(new DropShadow(5, Color.GRAY));
    }

    /// notification

    private void showNotification(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        // Show the alert
        alert.show();

        // Create a timeline to hide the alert after 2 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> alert.close()));
        timeline.setCycleCount(1);
        timeline.play();
    }

}
