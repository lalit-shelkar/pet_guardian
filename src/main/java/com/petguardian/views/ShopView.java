package com.petguardian.views;

import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ShopView {
        private Pet app;
        private Pane rootpane;

        public ShopView(Pet app) {
                this.app = app;
                initilization();
        }

        private void initilization() {
                rootpane = new Pane();
                rootpane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 50% 50% , #F5D7C3, #ffffff);");

                // Load images
                Image dogImage = new Image("ShopView/canva1.jpg");
                Image dogImage1 = new Image("ShopView/pet2.jpg");
                Image dogImage2 = new Image("ShopView/cat3.jpg");
                Image dogImage3 = new Image("ShopView/pets3.jpg");
                Image dogImage4 = new Image("ShopView/pet6.jpg");
                Image dogImage5 = new Image("ShopView/pet7.jpg"); // Replace with actual image path

                // Create UI elements
                Label discountLabel8 = new Label("FLAT DISCOUNT 25% V");
                discountLabel8.setTextFill(Color.ORANGE);
                discountLabel8.setStyle("-fx-font-weight: bold;");
                discountLabel8.setFont(new Font(25));
                Label dogLabel8 = new Label("Organic Food For \n Dogs & Puppies");

                dogLabel8.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
                Button dogButton8 = new Button("Shop Now");
                dogButton8.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton8.setFont(new Font(15));
                VBox dogBox = new VBox(10, discountLabel8, dogLabel8, dogButton8);

                dogBox.setPrefSize(840, 500);
                dogBox.setAlignment(Pos.CENTER_RIGHT);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox.setPadding(new Insets(0, 100, 0, 0));
                BackgroundImage backgroundImage = new BackgroundImage(dogImage, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(840, 500, false, false, false, false));
                dogBox.setBackground(new Background(backgroundImage));
                // -------------------------------------------------------------------------------------
                Label discountLabel1 = new Label("ON HYGIENE");
                discountLabel1.setTextFill(Color.ORANGE);
                discountLabel1.setStyle("-fx-font-weight: bold;");
                discountLabel1.setFont(new Font(25));
                Label dogLabel1 = new Label("AND PET CARE ITEMS \n Up to 10% off");

                dogLabel1.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
                Button dogButton1 = new Button("Shop Now");
                dogButton1.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton1.setFont(new Font(15));
                VBox dogBox1 = new VBox(10, discountLabel1, dogLabel1, dogButton1);

                dogBox1.setPrefSize(540, 500);
                dogBox1.setAlignment(Pos.TOP_CENTER);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox1.setPadding(new Insets(50, 0, 0, 0));
                BackgroundImage backgroundImage1 = new BackgroundImage(dogImage1, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(540, 500, false, false, false, false));
                dogBox1.setBackground(new Background(backgroundImage1));
                // ---------------------------------------------------------------
                Label discountLabel2 = new Label("CATS FOOD");
                discountLabel2.setTextFill(Color.ORANGE);
                discountLabel2.setStyle("-fx-font-weight: bold;");
                discountLabel2.setFont(new Font(25));
                Label dogLabel2 = new Label("Discount up to 25%");

                dogLabel2.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
                dogLabel2.setAlignment(Pos.TOP_CENTER);
                Button dogButton2 = new Button("Shop Now");
                dogButton2.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton2.setFont(new Font(15));
                VBox dogBox2 = new VBox(10, discountLabel2, dogLabel2, dogButton2);

                dogBox2.setPrefSize(520, 500);
                dogBox2.setAlignment(Pos.TOP_CENTER);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox2.setPadding(new Insets(50, 50, 50, 50));
                BackgroundImage backgroundImage2 = new BackgroundImage(dogImage2, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(520, 500, false, false, false, false));
                dogBox2.setBackground(new Background(backgroundImage2));
                dogBox2.setOnMouseClicked(e -> {
                        app.navigateToCatFood();
                });
                /// -------------------------------------------------------------------------------------------------------------------------------------////
                // Create UI elements
                Label discountLabel3 = new Label("SALE");
                discountLabel3.setTextFill(Color.ORANGE);
                discountLabel3.setStyle("-fx-font-weight: bold;");
                discountLabel3.setFont(new Font(25));
                Label dogLabel3 = new Label("Organic Food ForDiscounts up to 20% \n  \t \tON ALL PURCHASES");
                dogLabel3.setAlignment(Pos.TOP_CENTER);

                dogLabel3.setStyle("-fx-font-size: 20pt; -fx-font-weight: bold;");
                Button dogButton3 = new Button("Shop Now");
                dogButton3.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton3.setFont(new Font(15));
                VBox dogBox3 = new VBox(10, discountLabel3, dogLabel3, dogButton3);

                dogBox3.setPrefSize(525, 500);
                dogBox3.setAlignment(Pos.TOP_CENTER);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox3.setPadding(new Insets(50, 0, 0, 0));
                BackgroundImage backgroundImage3 = new BackgroundImage(dogImage3, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(525, 500, false, false, false, false));
                dogBox3.setBackground(new Background(backgroundImage3));

                ///
                // -------------------------------------------------------------------------------------
                Label discountLabel4 = new Label("10% OFF");
                discountLabel4.setTextFill(Color.ORANGE);
                discountLabel4.setStyle("-fx-font-weight: bold;");
                discountLabel4.setFont(new Font(25));
                discountLabel4.setAlignment(Pos.TOP_CENTER);
                Label dogLabel4 = new Label(" \t Hygiene \n ACCESSORIES FOR \n \tDOGS");
                dogLabel4.setAlignment(Pos.TOP_CENTER);
                dogLabel4.setStyle("-fx-background-color:black");

                dogLabel4.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
                Button dogButton4 = new Button("Shop Now");
                dogButton4.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton4.setFont(new Font(15));
                VBox dogBox4 = new VBox(10, discountLabel4, dogLabel4, dogButton4);

                dogBox4.setPrefSize(525, 500);
                dogBox4.setAlignment(Pos.TOP_CENTER);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox4.setPadding(new Insets(50, 0, 0, 0));
                BackgroundImage backgroundImage5 = new BackgroundImage(dogImage5, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(525, 500, false, false, false, false));
                dogBox4.setBackground(new Background(backgroundImage5));
                // ---------------------------------------------------------------
                Label discountLabel5 = new Label("YOUR PET HAPPY");
                discountLabel5.setTextFill(Color.ORANGE);
                discountLabel5.setStyle("-fx-font-weight: bold;");
                discountLabel5.setFont(new Font(25));
                Label dogLabel5 = new Label("Prime Members Save \n 30%");
                dogLabel5.setAlignment(Pos.TOP_CENTER);

                dogLabel5.setStyle("-fx-font-size: 25pt; -fx-font-weight: bold;");
                Button dogButton5 = new Button("Shop Now");
                dogButton5.setStyle(
                                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: black;-fx-background-radius:10;-fx-font-weight: bold;");

                dogButton5.setFont(new Font(15));
                VBox dogBox5 = new VBox(10, discountLabel5, dogLabel5, dogButton5);

                dogBox5.setPrefSize(840, 500);
                dogBox5.setAlignment(Pos.CENTER_LEFT);

                // dogBox.setStyle("-fx-background-color:red");

                dogBox5.setPadding(new Insets(0, 0, 0, 100));
                BackgroundImage backgroundImage6 = new BackgroundImage(dogImage4, BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                                new BackgroundSize(840, 500, false, false, false, false));
                dogBox5.setBackground(new Background(backgroundImage6));

                /// appbar import
                Navbar obj = new Navbar(app);
                HBox appBar = new HBox(900, backButton(), obj.navBar());
                appBar.setLayoutX(20);
                appBar.setLayoutY(10);

                //
                HBox hb = new HBox(dogBox, dogBox1, dogBox2);
                hb.setMaxWidth(1950);
                HBox hb1 = new HBox(dogBox3, dogBox4, dogBox5);

                VBox vb1 = new VBox(appBar, hb, hb1);

                // Create scene and stage
                Group gr = new Group(vb1);

                rootpane.getChildren().add(gr);

                // navigate
                dogBox.setOnMouseClicked(e -> app.navigateToDogFoodView());
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

        public Pane getView() {
                return rootpane;
        }
}
