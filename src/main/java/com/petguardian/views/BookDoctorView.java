package com.petguardian.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonObject;
import com.petguardian.Exceptions.DayNotAvalable;
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.DoctorModelClass.AvailableDay;
import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class BookDoctorView {
    private Pet app;
    private DoctorModelClass doctor;
    private Pane rootPane;
    private boolean isAppointment = false;
    private Button selectedDayButton = null;// Track the selected button
    private Button selectedTimeButton = null;

    private GridPane timeSlotsGrid = new GridPane(); // GridPane for time slots

    public BookDoctorView(Pet app, DoctorModelClass doctor) {
        this.app = app;
        this.doctor = doctor;
        initialize();
    }

    private void initialize() {
        rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: white;");

        rootPane.getChildren().addAll(leftVBox(), createDoctorCard(), appBar());
    }

    public Pane getView() {
        return rootPane;
    }

    private VBox leftVBox() {
        VBox leftVBox = new VBox(40);
        leftVBox.setMinSize(320, 930);
        leftVBox.setLayoutY(105);
        leftVBox.setStyle(
                "-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff);" +
                        "-fx-border-color: black;" + // Set the border color
                        // Set the border width
                        "-fx-border-style: solid;" + // Set the border style (solid, dashed, dotted, etc.)
                        "-fx-border-radius: 20px;" + // Set the border radius
                        "-fx-background-radius: 20px;" // Set the background radius to match border radius
        );
        ///
        ImageView doctorImageView = new ImageView(new Image(
                "dashboard/logo.png"));
        doctorImageView.setFitWidth(300);
        doctorImageView.setFitHeight(300);
        doctorImageView.setPreserveRatio(true);
        ///

        Label title = new Label("Your Pet Deserves  the Best Care");
        title.setAlignment(Pos.CENTER);
        title.setStyle(" -fx-font-size: 30px; -fx-font-style: italic;");
        title.setWrapText(true);
        title.setMaxWidth(250);
        Label title2 = new Label(" Book Now!");
        title2.setStyle("-fx-text-fill: green; -fx-font-size: 25px; -fx-font-style: italic;");

        leftVBox.getChildren().addAll(doctorImageView, title, title2);
        leftVBox.setAlignment(Pos.CENTER);
        return leftVBox;
    }

    private HBox createDoctorCard() {
        VBox leftVBox = createLeftVBox();
        VBox contactVBox = createContactVBox();

        HBox mainHBox = new HBox(50, leftVBox, contactVBox, createPatientForm());
        mainHBox.setPadding(new Insets(20));
        // mainHBox.setStyle(
        // "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color:
        // #e0e0e0; -fx-border-radius: 10;");
        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setEffect(new DropShadow(5, Color.GRAY));
        mainHBox.setLayoutX(400);
        mainHBox.setLayoutY(200);

        return mainHBox;
    }

    private HBox appBar() {
        HBox appBox = new HBox(900);
        appBox.setPadding(new Insets(30, 30, 30, 30));
        appBox.setStyle("-fx-background-color:#002240;");
        appBox.setMinWidth(1950);
        HBox hb = new HBox();

        hb.setSpacing(65);
        // hb.setAlignment(Pos.CENTER);

        Label home = new Label("Home");
        home.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label service = new Label("Service");
        service.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        Label shop = new Label("Shop");
        shop.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label cart = new Label("Cart");
        cart.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label profile = new Label("Profile");
        profile.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        Label notification = new Label("Notifications");
        notification.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        home.setFont(new Font(20));
        service.setFont(new Font(20));
        shop.setFont(new Font(20));
        cart.setFont(new Font(20));
        profile.setFont(new Font(20));
        notification.setFont(new Font(20));

        home.setOnMouseClicked(e -> app.navigateToHomeView());
        shop.setOnMouseClicked(e -> app.navigateToShopView());
        cart.setOnMouseClicked(e -> app.navigateToShopCardView());
        service.setOnMouseClicked(e -> app.navigateToVetarnaryView());

        hb.getChildren().addAll(home, service, shop, cart, profile, notification);

        appBox.getChildren().addAll(createBackButton(), hb);

        return appBox;
    }

    private VBox createLeftVBox() {
        StackPane imageContainer = createImageContainer();
        VBox leftVBox = new VBox(10, imageContainer, createNameLabel(), createQualificationLabel(),
                createExperienceLabel(),
                createRatingBox(), createTagsBox(), createDescriptionLabel());
        leftVBox.setAlignment(Pos.TOP_LEFT);
        return leftVBox;
    }

    private StackPane createImageContainer() {
        ImageView doctorImageView = new ImageView(new Image(doctor.getImg()));
        doctorImageView.setFitWidth(350);
        doctorImageView.setFitHeight(350);
        doctorImageView.setPreserveRatio(true);
        StackPane imageContainer = new StackPane(doctorImageView);
        imageContainer.setPrefSize(350, 350);
        imageContainer.setMaxSize(350, 350);
        imageContainer.setMinSize(350, 350);
        imageContainer.setStyle("-fx-border-color: black; -fx-border-width: 0.5;");

        return imageContainer;
    }

    private Label createNameLabel() {
        Label nameLabel = new Label(doctor.getName());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 30)); // Make font bold and italic
        nameLabel.setTextFill(Color.BLACK);
        return nameLabel;
    }

    private Label createQualificationLabel() {
        Label qualificationLabel = new Label(doctor.getQualification());
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        qualificationLabel.setTextFill(Color.BLACK);
        return qualificationLabel;
    }

    private Label createExperienceLabel() {
        Label experienceLabel = new Label(doctor.getExperience() + " Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        experienceLabel.setTextFill(Color.BLACK);
        return experienceLabel;
    }

    private HBox createRatingBox() {
        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label(String.valueOf(doctor.getRating()) + "/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        ratingLabel.setTextFill(Color.BLACK);
        ratingBox.getChildren().add(ratingLabel);

        for (int i = 0; i < (int) doctor.getRating(); i++) {
            Image star = new Image("vetarnary/star.png");
            ImageView starView = new ImageView(star);
            starView.setFitHeight(20);
            starView.setFitWidth(20);
            ratingBox.getChildren().add(starView);
        }
        if (doctor.getRating() % 1 != 0) {
            Image halfStar = new Image("vetarnary/halfStar.png");
            ImageView halfStarView = new ImageView(halfStar);
            halfStarView.setFitHeight(20);
            halfStarView.setFitWidth(20);
            ratingBox.getChildren().add(halfStarView);
        }
        return ratingBox;
    }

    private HBox createTagsBox() {
        HBox tagsBox = new HBox(10);
        for (String specialization : doctor.getTags()) {
            Label tagLabel = new Label(specialization);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagLabel.setTextFill(Color.BLACK);
            tagsBox.getChildren().add(tagLabel);
        }
        return tagsBox;
    }

    private Label createDescriptionLabel() {
        Label descriptionLabel = new Label(doctor.getAbout());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);
        descriptionLabel.setTextFill(Color.BLACK);
        return descriptionLabel;
    }

    private VBox createContactVBox() {
        VBox contactVBox = new VBox(20);
        contactVBox.setAlignment(Pos.CENTER_LEFT);
        contactVBox.setPadding(new Insets(10));

        contactVBox.getChildren().addAll(
                createLocationLabel(),
                createAvailabilityLabel(),
                createSpecialistLabel(),
                createPriceLabel(),
                createSelectDate(),
                createDaysBox(),
                timeSlotsGrid, // Add time slots grid to the contact VBox
                createCallButton() // Add patient form here
        ); // Add patient form here

        return contactVBox;
    }

    private Label createLocationLabel() {
        Label locationLabel = new Label("Location: " + doctor.getLocation());
        locationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 19));
        locationLabel.setTextFill(Color.BLACK);
        return locationLabel;
    }

    private Label createAvailabilityLabel() {
        Label availabilityLabel = new Label(doctor.isAvailable() ? "Available" : "Not Available");
        availabilityLabel.setTextFill(doctor.isAvailable() ? Color.GREEN : Color.RED);
        availabilityLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        availabilityLabel.setStyle("-fx-border-color: " + (doctor.isAvailable() ? "#4CAF50" : "red") + "; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");
        return availabilityLabel;
    }

    private Label createSpecialistLabel() {
        Label specialistLabel = new Label("Specializes : " + doctor.getSpecializes());
        specialistLabel.setTextFill(Color.GREEN);
        specialistLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 23)); // Make font bold and
                                                                                              // italic

        return specialistLabel;
    }

    private Label createPriceLabel() {
        Label priceLabel = new Label("Price : ₹" + doctor.getPrice());
        priceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        priceLabel.setTextFill(Color.BLACK);
        return priceLabel;
    }

    private Label createSelectDate() {
        Label priceLabel = new Label("Pick a one Date :");
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        priceLabel.setTextFill(Color.BLACK);
        return priceLabel;
    }

    private Label createTime() {
        Label priceLabel = new Label("Select Time :");
        priceLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        priceLabel.setTextFill(Color.BLACK);
        return priceLabel;
    }

    private HBox createDaysBox() {
        HBox daysBox = new HBox(10);
        daysBox.setAlignment(Pos.CENTER);

        List<Button> dayCheckBoxes = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM-dd");

        for (int i = 0; i < 5; i++) {
            LocalDate date = today.plusDays(i);
            String formattedDate = date.format(outputFormatter);
            Button dayButton = createDayButton(formattedDate);
            dayCheckBoxes.add(dayButton);
            daysBox.getChildren().add(dayButton);
        }
        return daysBox;
    }

    private Button createDayButton(String formattedDate) {
        boolean isAvailable = false;

        for (AvailableDay availableDay : doctor.getAvailableDays()) {
            if (availableDay.getDate().equals(formattedDate)) {
                isAvailable = true;
                break;
            }
        }

        Button dayButton = new Button(formattedDate);

        if (isAvailable) {
            dayButton.setStyle(
                    "-fx-background-color: white; " +
                            "-fx-text-fill: green; " +
                            "-fx-font-size: 16px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-border-color: green; " +
                            "-fx-border-width: 1px;");
            dayButton.setOnAction(e -> {
                try {
                    handleButtonClick(dayButton, true);
                } catch (DayNotAvalable e1) {
                    showAlertBox(e1.getMessage());
                }
            }); // Add event handler for green buttons
        } else {
            dayButton.setStyle(
                    "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-size: 16px; -fx-background-radius: 5px; -fx-opacity: 0.5;-fx-border-color: grey");

            dayButton.setOnAction(e -> {
                try {
                    handleButtonClick(dayButton, false);
                } catch (DayNotAvalable e1) {
                    showAlertBox(e1.getMessage());
                }
            }); // Add event handler for grey buttons
        }
        return dayButton;
    }

    private VBox createCallButton() {
        Button callButton = new Button("Call: " + doctor.getContact());
        callButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        callButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 20px; " +
                        "-fx-background-radius: 10; " +
                        "-fx-pref-width: 300; " +
                        "-fx-pref-height: 35; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);");

        callButton.setOnMouseEntered(e -> callButton.setStyle(
                "-fx-background-color: #45a049; " + // Darker green on hover
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 20px; " +
                        "-fx-background-radius: 10; " +
                        "-fx-pref-width: 300; " +
                        "-fx-pref-height: 35; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);"));

        callButton.setOnMouseExited(e -> callButton.setStyle(
                "-fx-background-color: #4CAF50; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: bold; " +
                        "-fx-font-size: 20px; " +
                        "-fx-background-radius: 10; " +
                        "-fx-pref-width: 300; " +
                        "-fx-pref-height: 35; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);"));

        VBox callButtonContainer = new VBox(callButton);

        VBox.setMargin(callButton, new Insets(50, 0, 0, 0));
        callButton.setOnAction(e -> {
            // Placeholder for call functionality
            showAlertBox("Calling the doctor.....");
        });
        return callButtonContainer;
    }

    //// handel button
    /// date selecte here
    private void handleButtonClick(Button button, boolean isAvailable) throws DayNotAvalable {
        List<String> AvailableTime = new ArrayList<>();

        if (isAvailable) {
            if (selectedDayButton != null) {
                selectedDayButton.setStyle(
                        "-fx-background-color: white; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-size: 16px; " +
                                "-fx-background-radius: 5px; " +
                                "-fx-border-color: green; " +
                                "-fx-border-width: 1px;");
            }
            button.setStyle(
                    "-fx-background-color: orange; -fx-text-fill: white;-fx-font-size: 16px; -fx-background-radius: 5px;");
            selectedDayButton = button;
            ///
            /// time slot for that selected daays
            for (AvailableDay availableDay : doctor.getAvailableDays()) {
                if (availableDay.getDate().equals(selectedDayButton.getText())) {
                    AvailableTime = availableDay.getTimes();
                }
            }

            // Show time slots below the selected date
            showTimeSlots(selectedDayButton.getText(), AvailableTime);
        } else {
            /////
            //
            /// my exception
            throw new DayNotAvalable("Only available dates can be selected");
        }
    }

    private void showTimeSlots(String date, List<String> AvailableTime) {

        // Clear previous time slots
        timeSlotsGrid.getChildren().clear();
        timeSlotsGrid.add(createTime(), 0 % 3, 0 / 3);
        timeSlotsGrid.setVgap(10); // Set vertical gap between cells
        timeSlotsGrid.setHgap(10); // Set horizontal gap between cells

        // Generate time slots
        String[] timeSlots = { "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM" };
        for (int i = 0; i < timeSlots.length; i++) {

            Button timeSlotButton = new Button(timeSlots[i]);

            /// give the color to time where time is present is green

            if (AvailableTime.contains(timeSlots[i])) {
                timeSlotButton.setStyle(
                        "-fx-background-color: white; " +
                                "-fx-text-fill: green; " +
                                "-fx-font-size: 16px; " +
                                "-fx-background-radius: 5px; " +
                                "-fx-border-color: green; " +

                                "-fx-border-width: 1px;");
                /// on click time button
                timeSlotButton.setOnAction(e -> {
                    handleTimeButton(timeSlotButton, true);
                });
            } else {
                timeSlotButton.setStyle(
                        "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-size: 16px; -fx-background-radius: 5px; -fx-opacity: 0.5;-fx-border-color: grey;");
                // on click time button
                timeSlotButton.setOnAction(e -> {
                    try {
                        throw new DayNotAvalable("Only available Time can be selected");
                    } catch (DayNotAvalable e1) {
                        showAlertBox(e1.toString());
                    }
                });
            }
            timeSlotsGrid.add(timeSlotButton, (i + 1) % 3, (i + 1) / 3); // Add buttons in a grid with 2 columns

        }
    }

    /// handel time
    private void handleTimeButton(Button button, boolean isAvailable) {

        if (selectedTimeButton != null) {
            selectedTimeButton.setStyle(
                    "-fx-background-color: white; " +
                            "-fx-text-fill: green; " +
                            "-fx-font-size: 16px; " +
                            "-fx-background-radius: 5px; " +
                            "-fx-border-color: green; " +
                            "-fx-border-width: 1px;");
        }
        button.setStyle(
                "-fx-background-color: orange; -fx-text-fill: white;-fx-font-size: 16px; -fx-background-radius: 5px;");
        selectedTimeButton = button;
    }

    /// paitint form crate kela ethe
    ////
    private VBox createPatientForm() {
        VBox patientFormVBox = new VBox(20);
        patientFormVBox.setAlignment(Pos.CENTER_LEFT);
        patientFormVBox.setPadding(new Insets(20));
        patientFormVBox.setStyle(
                "-fx-background-color: linear-gradient(from 50% 50% to 0% 0%, #F5D7C3, #ffffff); " +
                        "-fx-background-radius: 10; " +
                        "-fx-border-color: #e0e0e0; " +
                        "-fx-border-radius: 10; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 10, 0, 0, 2);");

        // Create labels and text fields for pet name, type, and age
        Label petNameLabel = new Label("Pet Name:");
        petNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField petNameField = new TextField();
        petNameField.setPromptText("Enter pet name");
        petNameField.setMinHeight(45);

        Label petTypeLabel = new Label("Pet Type:");
        petTypeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField petTypeField = new TextField();
        petTypeField.setPromptText("Enter pet type");
        petTypeField.setMinHeight(45);

        Label petAgeLabel = new Label("Pet Age:");
        petAgeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextField petAgeField = new TextField();
        petAgeField.setPromptText("Enter pet age");
        petAgeField.setMinHeight(45);

        Label symptoms = new Label("What are your symptoms:");
        symptoms.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        TextArea symptomsField = new TextArea();
        symptomsField.setPromptText("Enter symptoms");
        symptomsField.setPrefRowCount(3);
        symptomsField.setWrapText(true);
        symptomsField.setStyle("-fx-font-size: 16px;");
        symptomsField.setPrefWidth(300);

        patientFormVBox.getChildren().addAll(
                petNameLabel, petNameField,
                petTypeLabel, petTypeField,
                petAgeLabel, petAgeField,
                symptoms, symptomsField,
                createAppointmentButton(petNameField, petTypeField, petAgeField, symptomsField));

        patientFormVBox.setMinHeight(600);
        patientFormVBox.setMinWidth(300);
        return patientFormVBox;
    }

    //// book apportment button
    private Button createAppointmentButton(TextField petNameField, TextField petTypeField, TextField petAgeField,
            TextArea symptomsField) {
        Button appointmentButton = new Button("Book Appointment");
        appointmentButton.setStyle(
                "-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 20px; -fx-background-radius: 10; -fx-pref-width: 300; -fx-pref-height: 35;");

        // Pass all required fields to handleSubmit method
        appointmentButton.setOnAction(event -> {
            handleSubmit(petNameField, petTypeField, petAgeField, symptomsField, appointmentButton);

        });
        return appointmentButton;
    }

    //// button click kelyvr
    private void toggleAppointment(Button appointmentButton) {

        //// submit kelyavar request janya sathi
        // handleSubmit(null, null, null);

        if (isAppointment) {
            appointmentButton.setText("Book Appointment");
            appointmentButton.setStyle(
                    "-fx-background-color: #FFA500; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 20px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-pref-width: 300; -fx-pref-height: 35;");
        } else {
            appointmentButton.setText("Cancel Appointment");
            appointmentButton.setStyle(
                    "-fx-background-color: red; " +
                            "-fx-text-fill: white; " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 20px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-pref-width: 300; -fx-pref-height: 35;");
        }
        isAppointment = !isAppointment;
    }

    //// post request for submiting booking apportment
    ///
    private void handleSubmit(TextField petNameField, TextField petTypeField, TextField petAgeField,
            TextArea symptomsField, Button appointmentButton) {
        // Get the text from the text fields
        String petName = petNameField.getText();
        String petType = petTypeField.getText();
        String petAge = petAgeField.getText();
        String symptoms = symptomsField.getText();

        // Validate the form fields
        if (petName.isEmpty() || petType.isEmpty() || petAge.isEmpty() || symptoms.isEmpty()) {
            showAlertBox("All fields must be filled out.");
            return;
        }

        // Validate selected day and time
        if (selectedDayButton == null) {
            showAlertBox("Please select a date.");
            return;
        }
        if (selectedTimeButton == null) {
            showAlertBox("Please select a time.");
            return;
        }

        // Create a new PatientModelClass instance and submit the details
        try {
            boolean result = bookAppotment(petName, petType, petAge, symptoms);
            if (result == false) {
                showAlertBox("server kadun problem.");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlertBox("Please try again." + e.getMessage());

            return;
        }
        // symptoms);

        // Show success message

        toggleAppointment(appointmentButton);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Appointment Booked");
        alert.setHeaderText(null);
        alert.setContentText("Your appointment has been booked successfully!");
        alert.show();
    }

    ////
    ////
    private Button createBackButton() {
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setMinSize(130, 40);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");
        backButton.setOnAction(e -> app.navigateToVetarnaryView());
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setMinSize(130, 40);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");
        backButton.setOnAction(e -> app.navigateToVetarnaryView());
        return backButton;
    }

    void showAlertBox(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Selection Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private boolean bookAppotment(String petName, String petType, String petAge, String symptoms) throws IOException {
        String apiUrl = "https://pet-api-two.vercel.app/createPatient";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        JsonObject requestBody = new JsonObject();

        Map<String, Object> userinfo = MyAuthentication.getUserInfo();

        requestBody.addProperty("patientId", userinfo.get("uid").toString());
        requestBody.addProperty("doctorId", doctor.getFirestoreId());
        requestBody.addProperty("name", userinfo.get("userName").toString());
        requestBody.addProperty("contact", userinfo.get("phone").toString());
        requestBody.addProperty("petName", petName);
        requestBody.addProperty("petAge", Integer.parseInt(petAge));
        requestBody.addProperty("petType", petType);
        requestBody.addProperty("symptoms", symptoms);
        requestBody.addProperty("appointmentDay", selectedDayButton.getText());
        requestBody.addProperty("appointmentTime", selectedTimeButton.getText());

        try (OutputStream os = conn.getOutputStream()) {
            os.write(requestBody.toString().getBytes());
            os.flush();
        }

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // HTTP 200
            return true;
        } else {
            // Print error details
            InputStream errorStream = conn.getErrorStream();
            if (errorStream != null) {
                String errorResponse = convertStreamToString(errorStream);
                System.err.println("Error Response: " + errorResponse);
            }
            return false;
        }
    }

    private String convertStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
