package com.petguardian.views;

import java.util.List;
import com.petguardian.Model.ProductModelClass;
import com.petguardian.controllers.Pet;
import com.petguardian.controllers.ProductDataFetch;
import com.petguardian.views.common.Navbar;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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

public class CatFoodView {
    private Pet app;
    private Pane rootPane;
    ImageView mainImageview;
    String Mainpricenum;
    Label MainLabel;
    Label Mainprice;
    ProductDataFetch productobj = new ProductDataFetch();
    List<ProductModelClass> productList = productobj.productList;

    public CatFoodView(Pet app) {
        this.app = app;
        this.rootPane = new Pane(); // Initialize the rootpane
        initialize();
    }

    private void initialize() {

        Label title = new Label("New Arrivals");
        title.setTextFill(Color.BLACK);
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(new Font(35));
        title.setLayoutX(800);
        title.setLayoutY(100);
        title.setAlignment(Pos.TOP_CENTER);

        if (!productList.isEmpty()) {
            ProductModelClass firstProduct = productList.get(0);
            Image image = new Image(firstProduct.getImg());
            mainImageview = new ImageView(image);
            mainImageview.setFitWidth(500);
            mainImageview.setFitHeight(480);
            mainImageview.setPreserveRatio(true);
            MainLabel = new Label(firstProduct.getName());
            MainLabel.setTextFill(Color.BLACK);
            MainLabel.setStyle("-fx-font-weight: bold;");
            MainLabel.setFont(new Font(20));
            MainLabel.setPadding(new Insets(15, 0, 0, 0));
            Mainpricenum = firstProduct.getPrice();
            Mainprice = new Label("Price: " + Mainpricenum);
            Mainprice.setTextFill(Color.BLACK);
            Mainprice.setFont(new Font(20));

        } else {
            Image image = new Image("dog.png");
            mainImageview = new ImageView(image);
            mainImageview.setFitWidth(500);
            mainImageview.setFitHeight(480);
            mainImageview.setPreserveRatio(true);
            MainLabel = new Label("Food Royal");
            MainLabel.setTextFill(Color.BLACK);
            MainLabel.setStyle("-fx-font-weight: bold;");
            MainLabel.setFont(new Font(20));
            MainLabel.setPadding(new Insets(15, 0, 0, 0));
            Mainprice = new Label("Price: " + Mainpricenum);
            Mainprice.setTextFill(Color.BLACK);
            Mainprice.setFont(new Font(20));
        }

        VBox vb1 = new VBox();
        vb1.getChildren().add(mainImageview);
        vb1.setPadding(new Insets(0, 20, 0, 20));
        vb1.setPrefHeight(500);
        vb1.setPrefWidth(500);
        vb1.setMinHeight(500);
        vb1.setMinWidth(500);
        vb1.setMaxHeight(500);
        vb1.setMaxWidth(500);
        vb1.setStyle("-fx-background-color: linear-gradient(to bottom,rgba(251,247,230,1),rgba(215,176,177,1))");
        vb1.setAlignment(Pos.CENTER);

        MainLabel = new Label("Food Royal");
        MainLabel.setTextFill(Color.BLACK);
        MainLabel.setStyle("-fx-font-weight: bold;");
        MainLabel.setFont(new Font(20));
        MainLabel.setPadding(new Insets(15, 0, 00, 0));

        Mainprice = new Label("price " + Mainpricenum);
        Mainprice.setTextFill(Color.BLACK);
        // price.setStyle("-fx-font-weight: bold;");
        Mainprice.setFont(new Font(20));

        Button Icon = new Button("Add to Cart");
        Icon.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

        Icon.setFont(new Font(15));
        Icon.setFocusTraversable(false);
        ////////
        /*
         * Icon.setOnMouseClicked(e -> {
         * 
         * app.addItemToCart(prObj);
         * showNotification("product Added Sucssesfully");
         * 
         * });
         */

        // ---------------------------------all box passes in main vbox
        VBox vb2 = new VBox(10, vb1, MainLabel, Mainprice, Icon);
        vb2.setMaxHeight(700);
        vb2.setMaxWidth(400);
        vb2.setStyle(
                "-fx-background-color:white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 5, 0, 0, 0);");

        vb2.setLayoutX(100);
        vb2.setLayoutY(200);
        vb2.setPadding(new Insets(20, 20, 20, 20));

        // complete------------------------------------------

        HBox hBoxScroll = new HBox(60);
        hBoxScroll.setPrefHeight(1000);
        hBoxScroll.setPrefWidth(1100);
        // hBoxScroll.setStyle("-fx-background-color:transparent; ");
        hBoxScroll.setLayoutX(900);
        hBoxScroll.setLayoutY(150);
        hBoxScroll.setPadding(new Insets(20, 50, 0, 50));
        hBoxScroll.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 50% 50%, #F5D7C3, #ffffff);");

        VBox vBox1 = new VBox(20);
        VBox vBox2 = new VBox(20);

        for (int i = 0; i < productList.size(); i = i + 2) {
            if (productList.get(i).getCategory().equalsIgnoreCase("catFood")) {
                vBox1.getChildren().add(foodcard(productList.get(i)));
                vBox2.getChildren().add(foodcard(productList.get(i + 1)));
            }
        }

        // for (ProductModelClass product : productList) {
        // if (product.getCategory().equalsIgnoreCase("catfood")) {
        // hBoxScroll.getChildren().add(foodcard(product));
        // }
        // }

        hBoxScroll.getChildren().addAll(vBox1, vBox2);
        ScrollPane sc = new ScrollPane(hBoxScroll);
        sc.setMaxSize(1000, 1000);
        sc.setLayoutX(900);
        sc.setLayoutY(180);
        sc.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sc.setStyle("-fx-background-color: transparent;");

        HBox hb = new HBox(120, vb2);
        // hb.setStyle("-fx-background-color: pink;");
        hb.setPrefHeight(900);
        hb.setPrefWidth(1900);
        hb.setPadding(new Insets(0, 0, 50, 70));
        // hb.setStyle("-fx-background-color: red");
        hb.setLayoutY(100);

        Group gr = new Group(title, vb2, appBar(), sc);
        StackPane root = new StackPane(gr);
        rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");
        rootPane.getChildren().add(root);
    }

    public Pane getView() {
        return rootPane;
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

    private VBox foodcard(ProductModelClass prObj) {

        Image image1 = new Image(prObj.getImg());
        ImageView imageview1 = new ImageView(image1);

        imageview1.setFitWidth(350);
        imageview1.setFitHeight(300);
        imageview1.setPreserveRatio(true);
        imageview1.setStyle("-fx-background-color: Transparent");

        VBox vb5 = new VBox(imageview1);
        vb5.setPrefHeight(200);
        vb5.setMaxWidth(400);
        vb5.setStyle("-fx-background-color: linear-gradient(to bottom,rgba(251,247,230,1),rgba(215,176,177,1))");
        vb5.setAlignment(Pos.CENTER);

        Label lb2 = new Label(prObj.getName());
        lb2.setTextFill(Color.BLACK);
        lb2.setStyle("-fx-font-weight: bold;");
        lb2.setFont(new Font(30));

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
        Mainpricenum = prObj.getPrice();
        price.setTextFill(Color.BLACK);
        // price.setStyle("-fx-font-weight: bold;");
        price.setFont(new Font(20));

        HBox ratingContainer = new HBox();
        ratingContainer.setAlignment(Pos.CENTER_RIGHT);
        ratingContainer.setPrefWidth(300);
        ratingContainer.getChildren().addAll(ratingLabel, ratingBox);

        Button Icon1 = new Button("Add to Cart");
        Icon1.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");
        Icon1.setOnMouseClicked(e -> app.addItemToCart(prObj));

        Icon1.setFont(new Font(15));
        Icon1.setFocusTraversable(false);

        // vb5.setPadding(new Insets(20, 0, 0, 20));

        VBox vb4 = new VBox(5, vb5, lb2, price, ratingContainer, Icon1);
        vb4.setMaxHeight(500);
        vb4.setMaxWidth(550);
        vb4.setStyle("-fx-background-color:pink;");
        vb4.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: black;" + // Set the border color
                        "-fx-border-style: solid;" + // Set the border style (solid, dashed, dotted, etc.)
                        "-fx-border-radius: 20px;" + // Set the border radius
                        "-fx-background-radius: 20px;");
        // vb4.setLayoutX(900);
        // vb4.setLayoutY(150);
        vb4.setPadding(new Insets(20, 50, 20, 50));
        vb4.setOnMouseEntered(event -> elevateCard(vb4));
        vb4.setOnMouseExited(event -> resetCardElevation(vb4));

        vb4.setOnMouseClicked(e -> {

            Mainpricenum = prObj.getPrice();
            mainImageview.setImage(new Image(prObj.getImg()));
            MainLabel.setText(prObj.getName());
            Mainprice.setText("Price : " + prObj.getPrice() + "          ");

        });
        return vb4;

    }

    private void elevateCard(VBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1.05);
        st.setToY(1.05);
        st.play();
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.GRAY);
        shadow.setRadius(10);
        card.setEffect(shadow);
    }

    private void resetCardElevation(VBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
        st.setToX(1);
        st.setToY(1);
        st.play();
        card.setEffect(null);
    }
}