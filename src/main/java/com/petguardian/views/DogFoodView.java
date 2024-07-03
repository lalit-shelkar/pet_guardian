package com.petguardian.views;

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
import javafx.util.Duration;

import java.util.List;

public class DogFoodView {
    private Pet app;
    private Pane rootpane;
    boolean isDog = true;

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
        Label lb1 = new Label("Dog");
        lb1.setTextFill(Color.ORANGE);
        lb1.setStyle("-fx-font-weight: bold;");
        lb1.setFont(new Font(30));
        ////
        // //cat Label
        Label catlabel = new Label("Cat");
        catlabel.setTextFill(Color.BLACK);
        catlabel.setStyle("-fx-font-weight: bold;");
        catlabel.setFont(new Font(30));

        // category box contain dog and cat category
        HBox categoryBox = new HBox(40, lb1, catlabel);
        categoryBox.setAlignment(Pos.TOP_CENTER);
        categoryBox.setPadding(new Insets(200, 0, 0, 100));

        ///

        HBox hb = new HBox(50);
        hb.setPrefHeight(600);
        hb.setPrefWidth(1900);
        hb.setLayoutY(300);
        hb.setPadding(new Insets(40, 0, 50, 70));

        try {
            // Fetch products and add to HBox
            ProductDataFetch productobj = new ProductDataFetch();
            List<ProductModelClass> productList = productobj.productList;
            for (ProductModelClass product : productList) {
                hb.getChildren().add(foodCard(product));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception (e.g., show an error message to the user)
        }

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

        Group gr = new Group(appBar(), scrollPane, title, categoryBox, hb);

        StackPane root = new StackPane(gr);
        root.setStyle("-fx-background-color: rgba(251,247,230,1);");

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
        backButton.setOnAction(e -> app.navigateToHomeView());
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
        box3.setPrefHeight(380);
        box3.setPrefWidth(300);
        box3.setStyle("-fx-background-color: linear-gradient(to bottom,rgba(251,247,230,1),rgba(215,176,177,1))");
        box3.setAlignment(Pos.CENTER);

        Label lbbo3 = new Label(prObj.getName());
        lbbo3.setTextFill(Color.BLACK);
        lbbo3.setStyle("-fx-font-weight: bold;");
        lbbo3.setFont(new Font(20));
        lbbo3.setPadding(new Insets(15, 0, 0, 0));

        Button boxButton3 = new Button("Add to Cart");
        boxButton3.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");
        boxButton3.setFont(new Font(15));

        VBox vb3 = new VBox(10, box3, lbbo3, boxButton3);
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
}
