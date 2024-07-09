package com.petguardian.views;

import com.petguardian.Model.ProductModelClass;
import com.petguardian.controllers.Pet;
import com.petguardian.views.common.Navbar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCardView {
    private final Pet app;
    private final Pane rootPane = new Pane();
    static final List<ProductModelClass> productList = new ArrayList<>();
    private final Map<ProductModelClass, Integer> productQuantities = new HashMap<>();
    private Label subtotalLabel;
    private Label totalLabel;
    private VBox productsList = new VBox(40); // Added as an instance variable

    public ShopCardView(Pet app) {
        this.app = app;
        initialize();
    }

    public void addProductFromExternal(ProductModelClass obj) {
        productList.add(obj);
        productQuantities.put(obj, 1);

        productsList.getChildren().add(createProductBox(obj));
        updateSubtotalAndTotal();
    }

    private void initialize() {
        // Sample product data initialization
        // Initialize quantities for each product
        for (ProductModelClass product : productList) {
            productQuantities.put(product, 1);
        }

        rootPane.setStyle("-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);");

        // Main VBox for the entire view
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));

        // Title Label
        Label title = new Label("Shopping Cart");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 34));
        title.setTextFill(Color.ORANGE);

        // Navbar
        HBox navbar = new HBox(900, title, new Navbar(app).navBar());

        // Products List VBox
        // Initialize the productsList VBox
        productList.forEach(product -> productsList.getChildren().add(createProductBox(product)));

        // Promo Code Section
        HBox promoCodeBox = new HBox(20);
        TextField promoCodeField = new TextField();
        promoCodeField.setPromptText("Add a promo code");
        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white;");
        promoCodeBox.getChildren().addAll(promoCodeField, applyButton);
        promoCodeBox.setAlignment(Pos.CENTER_RIGHT);

        // Cart Summary VBox
        VBox cartSummary = new VBox(30);
        cartSummary.setPadding(new Insets(20));
        cartSummary.setStyle("-fx-border-color: grey; -fx-border-width: 1; -fx-background-radius: 10;");
        subtotalLabel = new Label("Subtotal: ₹" + calculateSubtotal());
        subtotalLabel.setTextFill(Color.BLACK);
        Label shippingLabel = new Label("Shipping: ₹9.99");
        shippingLabel.setTextFill(Color.BLACK);
        Label promoLabel = new Label("Free Shipping Promo: -₹9.99");
        promoLabel.setTextFill(Color.GREEN);
        totalLabel = new Label("Total Cost: ₹" + calculateTotal());
        totalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        totalLabel.setTextFill(Color.BLACK);
        cartSummary.getChildren().addAll(subtotalLabel, shippingLabel, promoLabel, totalLabel);

        // Checkout Buttons HBox
        HBox checkoutBox = new HBox(20);
        Button gPayButton = new Button("G Pay");
        gPayButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
        Button shopPayButton = new Button("Buy with Shop Pay");
        shopPayButton.setStyle("-fx-background-color: #6A1B9A; -fx-text-fill: white;");
        Button paypalButton = new Button("Buy with PayPal");
        paypalButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: black;");
        checkoutBox.getChildren().addAll(gPayButton, shopPayButton, paypalButton);
        checkoutBox.setAlignment(Pos.CENTER);

        // Sidebar VBox
        VBox sideBar = new VBox(35, promoCodeBox, cartSummary, checkoutBox);
        sideBar.setAlignment(Pos.TOP_CENTER); // Align to the top
        sideBar.setPadding(new Insets(20));
        sideBar.setStyle(
                "-fx-background-color: #ffffff; -fx-background-radius: 10; -fx-border-color: lightgrey; -fx-border-width: 1;");

        // Main content HBox
        HBox mainContent = new HBox(50, productsList, sideBar);
        mainContent.setAlignment(Pos.CENTER);

        // ScrollPane for root content
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPrefHeight(900);
        scrollPane.setPrefWidth(1980);

        // Set the scroll pane's background to transparent
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        // Set preferred size for scroll pane (optional)
        scrollPane.setPrefViewportWidth(800); // Adjust as needed
        scrollPane.setPrefViewportHeight(600); // Adjust as needed

        root.getChildren().addAll(navbar, scrollPane);
        rootPane.getChildren().add(root);
    }

    private HBox createProductBox(ProductModelClass product) {
        // Product HBox setup
        HBox productBox = new HBox(30);
        productBox.setStyle(
                "-fx-border-color: transparent; -fx-background-radius: 10; -fx-background-color: linear-gradient(to bottom right, #FFF1E0, #FFE5D9); -fx-border-width: 2;");
        productBox.setEffect(new DropShadow(10, Color.GRAY));

        // Product Image
        ImageView productImage = new ImageView(new Image(product.getImg()));
        productImage.setFitHeight(200);
        productImage.setFitWidth(200);
        productImage.setPreserveRatio(true);

        // Product Details VBox
        VBox productDetails = new VBox(20);
        Label nameLabel = new Label(product.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        nameLabel.setTextFill(Color.BLACK);
        Label sizeLabel = new Label("Rating: " + product.getRating());
        sizeLabel.setFont(Font.font("Arial", 18));
        sizeLabel.setTextFill(Color.BLACK);
        Label colorLabel = new Label("Category: " + product.getCategory());
        colorLabel.setFont(Font.font("Arial", 18));
        colorLabel.setTextFill(Color.BLACK);
        Label priceLabel = new Label("₹" + product.getPrice());
        priceLabel.setFont(Font.font("Arial", 20));
        priceLabel.setTextFill(Color.BLACK);
        productDetails.getChildren().addAll(nameLabel, sizeLabel, colorLabel, priceLabel);

        // Quantity and Buttons VBox
        VBox quantityBox = new VBox(10);
        HBox quantityControls = new HBox(5);
        Button decreaseButton = new Button("-");
        TextField quantityField = new TextField("1");
        quantityField.setPrefWidth(50);
        Button increaseButton = new Button("+");
        quantityControls.getChildren().addAll(decreaseButton, quantityField, increaseButton);
        quantityControls.setAlignment(Pos.CENTER);
        Label totalLabel = new Label("Total: ₹" + product.getPrice());
        totalLabel.setFont(Font.font("Arial", 20));
        totalLabel.setTextFill(Color.BLACK);
        quantityBox.getChildren().addAll(quantityControls, totalLabel);
        quantityBox.setAlignment(Pos.CENTER);

        // Move to Wishlist and Delete Buttons VBox
        VBox actionButtons = new VBox(20);
        Button moveToWishlistButton = new Button("Move to Wishlist");
        moveToWishlistButton.setStyle("-fx-background-color: #8BC34A; -fx-text-fill: white; -fx-background-radius: 5;");
        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #FF5252; -fx-text-fill: white; -fx-background-radius: 5;");
        deleteButton.setOnMouseClicked(e -> removeCard(product));
        actionButtons.getChildren().addAll(moveToWishlistButton, deleteButton);
        actionButtons.setAlignment(Pos.CENTER_RIGHT);

        // Adding elements to productBox HBox
        productBox.getChildren().addAll(productImage, productDetails, quantityBox, actionButtons);

        // Hover effect for elevation
        productBox.setOnMouseEntered(e -> productBox.setEffect(new DropShadow(20, Color.DARKGRAY)));
        productBox.setOnMouseExited(e -> productBox.setEffect(new DropShadow(10, Color.GRAY)));

        // Quantity Controls Logic
        increaseButton.setOnAction(e -> {
            int quantity = Integer.parseInt(quantityField.getText());
            quantity++;
            quantityField.setText(String.valueOf(quantity));
            productQuantities.put(product, quantity);
            totalLabel.setText("Total: ₹" + (quantity * Integer.parseInt(product.getPrice())));
            updateSubtotalAndTotal();
        });

        decreaseButton.setOnAction(e -> {
            int quantity = Integer.parseInt(quantityField.getText());
            if (quantity > 1) {
                quantity--;
                quantityField.setText(String.valueOf(quantity));
                productQuantities.put(product, quantity);
                totalLabel.setText("Total: ₹" + (quantity * Integer.parseInt(product.getPrice())));
                updateSubtotalAndTotal();
            }
        });

        return productBox;
    }

    private void updateSubtotalAndTotal() {
        subtotalLabel.setText("Subtotal: ₹" + calculateSubtotal());
        totalLabel.setText("Total Cost: ₹" + calculateTotal());
    }

    private int calculateSubtotal() {
        int subtotal = 0;
        for (Map.Entry<ProductModelClass, Integer> entry : productQuantities.entrySet()) {
            ProductModelClass product = entry.getKey();
            int quantity = entry.getValue();
            subtotal += Integer.parseInt(product.getPrice()) * quantity;
        }
        return subtotal;
    }

    private int calculateTotal() {
        int total = calculateSubtotal() - 9 + 9; // Example calculation with promo and shipping
        return total;
    }

    private void removeCard(ProductModelClass obj) {
        productList.remove(obj);
        productQuantities.remove(obj); // Remove the product from productQuantities
        refreshProductList(); // Call the method to refresh the product list
        updateSubtotalAndTotal(); // Update subtotal and total
    }

    private void refreshProductList() {
        productsList.getChildren().clear(); // Clear the current children
        productList.forEach(product -> productsList.getChildren().add(createProductBox(product))); // Add updated
                                                                                                   // product boxes
    }

    public Pane getView() {
        return rootPane;
    }
}
