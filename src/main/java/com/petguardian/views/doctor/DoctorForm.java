package com.petguardian.views.doctor;

import javafx.animation.ScaleTransition;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

import javafx.util.Duration;
import org.json.JSONObject;

import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;
import com.petguardian.util.CloudinaryConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DoctorForm {
    private File selectedImageFile;
    private Image selectedImage;
    private TextField nameField;
    private TextField experienceField;
    private TextField ratingField;
    private TextField tagsField;
    private TextField aboutField;
    private TextField locationField;
    private TextField specializesField;
    private TextField contactField;
    private TextField priceField;
    private TextField qualificationField;
    private TextField availableField;
    private TextField timeField;
    //
    Pet app;
    BorderPane root;

    public DoctorForm(Pet app) {
        this.app = app;
        initilizet();
    }

    private void initilizet() {

        root = new BorderPane();
        root.setPadding(new Insets(20));

        // SplitPane to divide screen
        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.5); // Split at half of the screen width

        // Left VBox with orange background
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        leftVBox.setPadding(new Insets(20));
        leftVBox.setSpacing(20);
        leftVBox.setStyle("-fx-background-color: #FFA500;");

        // Heading in white color
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 64));
        headerLabel.setTextFill(Color.WHITE);
        leftVBox.getChildren().add(headerLabel);

        // Animated text
        Label animatedTextLabel = new Label("Go Through Registration Form With Us Easily");
        animatedTextLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        animatedTextLabel.setTextFill(Color.WHITE);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), animatedTextLabel);
        scaleTransition.setFromX(1);
        scaleTransition.setToX(1.2);
        scaleTransition.setFromY(1);
        scaleTransition.setToY(1.2);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
        scaleTransition.play();
        leftVBox.getChildren().add(animatedTextLabel);

        // Image placeholder (adjust as per your design)
        ImageView imageView = new ImageView("doctorForm/doctor-form.png");
        imageView.setFitWidth(400);
        imageView.setFitHeight(400);
        leftVBox.getChildren().add(imageView);

        splitPane.getItems().add(leftVBox);

        // Right side form layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #d3d3d3; -fx-border-radius: 10; -fx-padding: 20;");

        int row = 0;

        // Image picker for doctor image
        Label imagePickerLabel = new Label("Doctor Image:");
        imagePickerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        imagePickerLabel.setTextFill(Color.BLACK);
        grid.add(imagePickerLabel, 0, row);

        Button imagePickerButton = new Button("Select Image");
        grid.add(imagePickerButton, 1, row++);
        imagePickerButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
            selectedImageFile = fileChooser.showOpenDialog(null);
            if (selectedImageFile != null) {
                try (FileInputStream input = new FileInputStream(selectedImageFile)) {
                    selectedImage = new Image(input);
                    imageView.setImage(selectedImage);
                    imagePickerButton.setText(selectedImageFile.getName());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        nameField = new TextField();
        experienceField = new TextField();
        ratingField = new TextField();
        tagsField = new TextField();
        aboutField = new TextField();
        locationField = new TextField();
        specializesField = new TextField();
        contactField = new TextField();
        priceField = new TextField();
        qualificationField = new TextField();
        availableField = new TextField();
        timeField = new TextField();

        addFormField(grid, "Name:", row++, nameField, 2);
        addFormField(grid, "Experience:", row++, experienceField, 2);
        addFormField(grid, "Rating:", row++, ratingField, 2);
        addFormField(grid, "Tags (comma separated):", row++, tagsField, 2);
        addFormField(grid, "About:", row++, aboutField, 2);
        addFormField(grid, "Location:", row++, locationField, 2);
        addFormField(grid, "Specializes:", row++, specializesField, 2);
        addFormField(grid, "Contact:", row++, contactField, 2);
        addFormField(grid, "Price:", row++, priceField, 2);
        addFormField(grid, "Qualification:", row++, qualificationField, 2);
        addFormField(grid, "Available:", row++, availableField, 2);

        // Get next 5 days
        LocalDate today = LocalDate.now();
        List<CheckBox> dayCheckBoxes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd");

        Label availableDaysLabel = new Label("Select Available Days:");
        availableDaysLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        availableDaysLabel.setTextFill(Color.BLACK);
        grid.add(availableDaysLabel, 0, row++, 2, 1);

        HBox daysBox = new HBox(10);
        daysBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < 5; i++) {
            LocalDate date = today.plusDays(i);
            CheckBox checkBox = new CheckBox(date.format(formatter));
            dayCheckBoxes.add(checkBox);
            daysBox.getChildren().add(checkBox);
        }
        grid.add(daysBox, 0, row++, 2, 1);

        // Time input field
        addFormField(grid, "Enter time (e.g., 09:00-17:00):", row++, timeField, 2);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #4682b4; -fx-text-fill: white; -fx-font-size: 14;");
        grid.add(submitButton, 0, row, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);

        // Label to display selected dates and time
        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 16;");
        grid.add(resultLabel, 0, row + 1, 2, 1);
        GridPane.setHalignment(resultLabel, HPos.CENTER);

        // Submit button action
        submitButton.setOnAction(e -> {
            if (validateFields() && validateImageSelection()) {
                try {
                    postDoctorAvailability(resultLabel, dayCheckBoxes);
                    app.navigateToDoctorDashboard();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        splitPane.getItems().add(grid);
        root.setCenter(splitPane);

    }

    /// validation function
    private boolean validateFields() {
        boolean isValid = true;

        // Validate name field
        if (nameField.getText().isEmpty()) {
            showAlert("Name field is required.");
            return false;

        }

        // Validate experience field (numeric check)
        if (!experienceField.getText().matches("\\d+")) {
            showAlert("Experience must be a number.");
            return false;
        }

        // Validate rating field (numeric check)
        if (!ratingField.getText().matches("\\d+")) {
            showAlert("Rating must be a number.");
            return false;
        }

        // Validate contact field (email format)

        return isValid;
    }

    /// validation for img
    private boolean validateImageSelection() {
        if (selectedImageFile == null) {
            showAlert("Please select a doctor image.");
            return false;
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void addFormField(GridPane grid, String labelText, int row, TextField textField, int colSpan) {
        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        label.setTextFill(Color.BLACK);
        grid.add(label, 0, row);
        textField.setPrefWidth(400);
        grid.add(textField, 1, row, colSpan, 1);
    }

    private void postDoctorAvailability(Label resultLabel, List<CheckBox> dayCheckBoxes) {
        // Handling the submission of doctor availability here
        // Example implementation using JSON and HTTP POST
        // Replace with your actual implementation
        CloudinaryConnection obj = new CloudinaryConnection();
        //
        String firestoreId = MyAuthentication.getUserUid(); // Example: MyAuthentication.getUserUid();
        String name = nameField.getText();
        String experience = experienceField.getText();
        String rating = ratingField.getText();
        String doctorImage = obj.uploadToCloudinary(selectedImageFile); // Example:
        // CloudinaryConnection.uploadToCloudinary(selectedImageFile);
        String tags = tagsField.getText();
        String about = aboutField.getText();
        String location = locationField.getText();
        String specializes = specializesField.getText();
        String contact = contactField.getText();
        String price = priceField.getText();
        String qualification = qualificationField.getText();
        String available = availableField.getText();

        List<String> selectedDates = new ArrayList<>();
        for (CheckBox checkBox : dayCheckBoxes) {
            if (checkBox.isSelected()) {
                selectedDates.add(checkBox.getText());
            }
        }
        String time = timeField.getText();

        // Example: Send data to server using HTTP POST
        try {
            postDoctorDataToServer(firestoreId, name, experience, rating, doctorImage, tags, about, location,
                    specializes, contact, price, qualification, available, selectedDates, time);
            resultLabel.setText("Data submitted successfully!");
        } catch (Exception ex) {
            resultLabel.setText("Error: " + ex.getMessage());
        }
    }

    private void postDoctorDataToServer(String firestoreId, String name, String experience, String rating,
            String doctorImage, String tags, String about, String location, String specializes, String contact,
            String price, String qualification, String available, List<String> selectedDates, String time)
            throws Exception {
        URL url = new URL("https://pet-api-two.vercel.app/createDoctor"); // Example URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("firestoreId", firestoreId);
        json.put("name", name);
        json.put("experience", experience);
        json.put("rating", rating);
        json.put("doctorImage", doctorImage);
        json.put("tags", tags);
        json.put("about", about);
        json.put("location", location);
        json.put("specializes", specializes);
        json.put("contact", contact);
        json.put("price", price);
        json.put("qualification", qualification);
        json.put("available", available);
        json.put("availableDays", String.join(" ", selectedDates));
        json.put("time", time);

        String input = json.toString();
        try (OutputStream os = conn.getOutputStream()) {
            os.write(input.getBytes(StandardCharsets.UTF_8));
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
    }

    public BorderPane getView() {
        return root;
    }

}
