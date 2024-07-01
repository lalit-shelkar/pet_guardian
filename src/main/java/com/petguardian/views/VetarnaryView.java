package com.petguardian.views;
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.controllers.Pet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VetarnaryView {
    private Pet app;
    private Pane rootpane;
    boolean islogin = true;

    public VetarnaryView(Pet app) {
        this.app = app;
        initialize();
    }

    private void initialize() {

        // main pane set
        rootpane = new Pane();
        rootpane.setStyle("-fx-background-color: linear-gradient(from  50% 50% to 0% 0% , #F5D7C3, #ffffff);");

        rootpane.getChildren().addAll(backButton(), getTopBox(), doctorCard());
    }

    public Pane getView() {
        return rootpane;
    }

    /// geting doctor card

    private HBox doctorCard() {
        //DoctorModelClass drobj = new DoctorModelClass();

        ImageView doctorImageView = new ImageView(new Image("vetarnary/doctor2.png"));
        doctorImageView.setFitWidth(250);
        doctorImageView.setFitHeight(250);
        doctorImageView.setPreserveRatio(true);

        // Doctor Information
        Label doctorNameLabel = new Label("Dr. Milind Joshi");
        doctorNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Label qualificationLabel = new Label("MBBS, MS - General Surgery");
        qualificationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        Label experienceLabel = new Label("23 Years Experience Overall");
        experienceLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Rating
        HBox ratingBox = new HBox(5);
        Label ratingLabel = new Label("4.7/5");
        ratingLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        ratingBox.getChildren().add(ratingLabel);

        for (int i = 0; i < 4; i++) {
            Image star = new Image("vetarnary/star.png");
            ImageView starView = new ImageView(star);
            starView.setFitHeight(20);
            starView.setFitWidth(20);
            ratingBox.getChildren().add(starView);
        }
        Image halfStar = new Image("vetarnary/halfStar.png");
        ImageView halfStarView = new ImageView(halfStar);
        halfStarView.setFitHeight(20);
        halfStarView.setFitWidth(20);
        ratingBox.getChildren().add(halfStarView);

        // Specialization tags
        HBox tagsBox = new HBox(10);
        String[] tags = { "Proctology", "Urology", "Laparoscopy", "Vascular", "Aesthetics" };
        for (String tag : tags) {
            Label tagLabel = new Label(tag);
            tagLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            tagLabel.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 5px;");
            tagsBox.getChildren().add(tagLabel);
        }

        // Doctor description
        Label descriptionLabel = new Label(
                "Dr. Milind Joshi is an expert and experienced General Surgeon, Proctologist, and Laparoscopic Surgeon with an experience of 23 years, and specializes in Proctology, Vascular, Laparoscopy, and Urology. He graduated and obtained his MBBS degree from...");
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(300);

        // Left VBox
        VBox leftVBox = new VBox(10, doctorNameLabel, qualificationLabel, experienceLabel, ratingBox, tagsBox,
                descriptionLabel);
        leftVBox.setAlignment(Pos.TOP_LEFT);

        // Contact Info
        VBox contactVBox = new VBox(20);
        contactVBox.setAlignment(Pos.CENTER);
        contactVBox.setPadding(new Insets(10));

        Label locationLabel = new Label("Location: Aanvii Hearing Solutions");
        locationLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Label availabilityLabel = new Label("Available");
        availabilityLabel.setTextFill(Color.GREEN);
        availabilityLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 24));
        availabilityLabel.setStyle("-fx-border-color: green; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 5px; " +
                "-fx-padding: 5px;");

        Label specialistLabel = new Label("Piles Specialist");

        specialistLabel.setTextFill(Color.GREEN);
        specialistLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 20));

        Button callButton = new Button("Call Us : 98224-38878");
        callButton.setStyle(
                "-fx-background-color: #FFA500; " + /* Orange background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-weight: bold; " + /* Bold text */
                        "-fx-font-size: 20px; " + /* Font size */
                        "-fx-background-radius: 10; " + /* Circular border radius */
                        "-fx-pref-width: 300; -fx-pref-height: 35;" /* Button width */
        );
        Button appointmentButton = new Button("Book Free Appointment");
        appointmentButton.setStyle(
                "-fx-background-color: #FFA500; " + /* Orange background */
                        "-fx-text-fill: white; " + /* White text */
                        "-fx-font-weight: bold; " + /* Bold text */
                        "-fx-font-size: 20px; " + /* Font size */
                        "-fx-background-radius: 10; " + /* Circular border radius */
                        "-fx-pref-width: 300; -fx-pref-height: 35;" /* Button width */
        );
        contactVBox.getChildren().addAll(locationLabel, availabilityLabel, specialistLabel, callButton,
                appointmentButton);

        // Main HBox
        // main box posion set here
        HBox mainHBox = new HBox(30, doctorImageView, leftVBox, contactVBox);
        mainHBox.setPadding(new Insets(20));
        mainHBox.setStyle(
                "-fx-background-color: white; -fx-background-radius: 10; -fx-border-color: #e0e0e0; -fx-border-radius: 10;");

        mainHBox.setAlignment(Pos.CENTER_LEFT);
        mainHBox.setLayoutX(150);
        mainHBox.setLayoutY(490);
        // apllying shadow for the card
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        shadow.setColor(Color.GRAY);
        mainHBox.setEffect(shadow);
        return mainHBox;
    }

    // detail doctor first VBox
    private VBox vBox1() {
        VBox cardVBox = new VBox();
        //

        return cardVBox;
    }

    // back Button

    private Button backButton() {
        Button backButton = new Button("Back");
        backButton.setLayoutX(20);
        backButton.setLayoutY(20);
        backButton.setMinSize(130, 40);
        backButton.setStyle(
                "-fx-background-color: linear-gradient(to right,yellow,orange); -fx-text-fill: White;-fx-background-radius:20;-fx-font-weight: bold;-fx-font-size:20");

        backButton.setOnAction(e -> {
            System.err.println("ka re bho");
            app.navigateToHomeView();
        });
        return backButton;
    }

    ///
    /// getting top heading
    BorderPane getTopBox() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLayoutX(20);
        mainLayout.setLayoutY(60);

        mainLayout.setPadding(new Insets(30, 30, 30, 30));

        // Top section
        VBox topSection = new VBox(20);
        topSection.setPadding(new Insets(20));
        topSection.setStyle("-fx-background-color:#002240  ; -fx-background-radius: 20;");
        topSection.setMinWidth(1200);
        topSection.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("Which Doctor to Consult for Piles - Top Piles Doctors in India");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 34));

        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.setPromptText("Select City");
        cityComboBox.getItems().addAll("pune", "mumbai ", "Narhe"); // Example cities

        HBox ratingBox = new HBox(10);
        Label ratingLabel = new Label("Rating");
        ratingLabel.setTextFill(Color.WHITE);
        ratingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Adding stars for rating
        for (int i = 0; i < 4; i++) {
            Image star = new Image("vetarnary/star.png"); // Use your own star image
            ImageView starView = new ImageView(star);
            starView.setFitHeight(30);
            starView.setFitWidth(30);
            ratingBox.getChildren().add(starView);
        }
        Image halfStar = new Image("vetarnary/halfStar.png"); // Use your own half star image
        ImageView halfStarView = new ImageView(halfStar);
        halfStarView.setFitHeight(30);
        halfStarView.setFitWidth(30);
        ratingBox.getChildren().add(halfStarView);

        ratingBox.getChildren().add(0, ratingLabel);

        HBox statsBox = new HBox(40);
        Label patientsLabel = new Label("2M+ Happy Patients");
        patientsLabel.setTextFill(Color.WHITE);
        patientsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        Label hospitalsLabel = new Label("700+ Hospitals");
        hospitalsLabel.setTextFill(Color.WHITE);
        hospitalsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        Label citiesLabel = new Label("40+ Cities");
        citiesLabel.setTextFill(Color.WHITE);
        citiesLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));

        statsBox.getChildren().addAll(patientsLabel, hospitalsLabel, citiesLabel);

        topSection.getChildren().addAll(titleLabel, cityComboBox, ratingBox, statsBox);

        // Right section for the doctor image
        ImageView doctorImage = new ImageView(new Image("vetarnary/doctor.png")); // Replace with actual doctor image
        doctorImage.setFitWidth(400);
        doctorImage.setPreserveRatio(true);

        HBox mainContent = new HBox(30);
        mainContent.getChildren().addAll(topSection, doctorImage);

        mainLayout.setCenter(mainContent);

        return mainLayout;
    }
}
