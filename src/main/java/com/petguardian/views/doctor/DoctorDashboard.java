package com.petguardian.views.doctor;

import com.petguardian.Model.PatientModelClass;
import com.petguardian.controllers.DoctorDataFetcher;
import com.petguardian.controllers.PatientDataFetcher;
import com.petguardian.controllers.Pet;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class DoctorDashboard {
    private Pet app;
    private BorderPane root;
    private List<PatientModelClass> patientsList;

    public DoctorDashboard(Pet app) {
        this.app = app;
        this.patientsList = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        // Initialize patients data
        PatientDataFetcher dataFetcher = new PatientDataFetcher();
        try {
            patientsList = dataFetcher.fetchPatientData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Main layout
        root = new BorderPane();

        VBox navigationPanel = new VBox();
        navigationPanel.setPrefWidth(200);
        navigationPanel.setSpacing(10); // Spacing between buttons
        navigationPanel.setPadding(new Insets(15));
        navigationPanel.setStyle("-fx-background-color: #2c3e50;"); // Dark background color

        // Add some buttons to the navigation panel
        ImageView logo = new ImageView(new Image("dashboard/logo.png"));
        logo.setFitHeight(200);
        logo.setFitWidth(200);
        logo.setStyle("-fx-background-color: #2c3e50;");
        logo.setPreserveRatio(true);

        Button dashboardButton = new Button("Dashboard");
        Button patientsButton = new Button("Patients");
        Button settingsButton = new Button("Settings");

        // Style the buttons
        String buttonStyle = "-fx-background-color: transparent; "
                + "-fx-text-fill: white; "
                + "-fx-font-size: 18px; "
                + "-fx-padding: 10px 20px; "
                + "-fx-border-width: 0 0 1 0; "
                + "-fx-border-color: #34495e; "
                + "-fx-border-style: solid;";

        dashboardButton.setStyle(buttonStyle);
        patientsButton.setStyle(buttonStyle);
        settingsButton.setStyle(buttonStyle);

        // Add buttons to the navigation panel
        navigationPanel.getChildren().addAll(logo, dashboardButton, patientsButton, settingsButton);

        // Set the left navigation panel in the BorderPane
        root.setLeft(navigationPanel);

        // Event handler for the Patients button
        patientsButton.setOnAction(event -> showPatients());

        // Placeholder for the main content area
        root.setCenter(new Label("Select an option from the navigation panel."));
    }

    private void showPatients() {
        GridPane patientsGrid = new GridPane();
        patientsGrid.setPadding(new Insets(15));
        patientsGrid.setHgap(15); // Horizontal gap between columns
        patientsGrid.setVgap(20); // Vertical gap between rows

        // Column constraints for equal width
        for (int i = 0; i < 7; i++) { // Adjusted to 7 columns including S. No.
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / 9);
            patientsGrid.getColumnConstraints().add(columnConstraints);
        }

        // Add headers to the grid
        addHeader(patientsGrid, "S. No.", 0);
        addHeader(patientsGrid, "Name", 1);
        addHeader(patientsGrid, "Contact", 2);
        addHeader(patientsGrid, "Pet Name", 3);
        addHeader(patientsGrid, "Pet Type", 4);
        addHeader(patientsGrid, "Pet Age", 5);
        addHeader(patientsGrid, "Symptoms", 6);
        addHeader(patientsGrid, "Appointment Day", 7);
        addHeader(patientsGrid, "Time", 8);
        addHeader(patientsGrid, "Status", 9);

        // Add patients dynamically
        for (int i = 0; i < patientsList.size(); i++) {
            PatientModelClass patient = patientsList.get(i);

            // S. No. column
            Label serialNumber = new Label(String.valueOf(i + 1));
            serialNumber.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(serialNumber, 0, i + 1);

            // Name column
            Label nameLabel = new Label(patient.getName());
            nameLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(nameLabel, 1, i + 1);

            // Contact column
            Label contactLabel = new Label(patient.getContact());
            contactLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(contactLabel, 2, i + 1);

             // Pet Name column
             Label petNameLabel = new Label(patient.getPetName());
             petNameLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
             patientsGrid.add(petNameLabel, 3, i + 1);

            // Pet Type column
            Label petTypeLabel = new Label(patient.getPetType());
            petTypeLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(petTypeLabel, 4, i + 1);

             // Pet Age column
             Label petAgeLabel = new Label(Integer.toString(patient.getPetAge()));
             petAgeLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
             patientsGrid.add(petAgeLabel, 5, i + 1);

              // Pet Symptomps column
              Label symptomsLabel = new Label(patient.getsymptoms());
              symptomsLabel.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
              patientsGrid.add(symptomsLabel, 6, i + 1);

            // Appointment Day & Time column
            Label appointmentDay = new Label(patient.getAppointmentDay());
            appointmentDay.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(appointmentDay, 7, i + 1);
            //
            Label appointmentTime = new Label(patient.getAppointmentTime());
            appointmentTime.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(appointmentTime, 8, i + 1);

            // Status column with toggle button
            Button statusButton = new Button(patient.getStatus());
            statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                    (patient.getStatus().equals("Pending") ? "#e67e22;" : "#2ecc71;") + // Orange for Pending, Green for Confirmed
                    "-fx-font-weight: bold; -fx-text-fill: white;");
            int finalI = i; // To use inside lambda expression
            statusButton.setOnAction(event -> {
                // Ask for confirmation
                if (confirmStatusChange(patient.getName())) {
                    // Toggle status
                    patientsList.get(finalI).setStatus(patientsList.get(finalI).getStatus().equals("Pending") ? "Confirmed" : "Pending");
                    // Update button text and background color
                    statusButton.setText(patientsList.get(finalI).getStatus());
                    statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                            (patientsList.get(finalI).getStatus().equals("Pending") ? "#e67e22;" : "#2ecc71;") +
                            "-fx-font-weight: bold; -fx-text-fill: white;");
                }
            });
            patientsGrid.add(statusButton, 9, i + 1);
        }

        root.setCenter(patientsGrid);
    }

    private void addHeader(GridPane grid, String text, int columnIndex) {
        Label header = new Label(text);
        header.setStyle("-fx-font-weight: bold; -fx-text-fill: #7f8c8d; -fx-padding: 5px;");
        grid.add(header, columnIndex, 0);
    }

    private boolean confirmStatusChange(String patientName) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Status Change");
        alert.setContentText("Are you sure you want to change the status of " + patientName + "?");

        // Customizing buttons text
        Button confirmButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);
        confirmButton.setText("Confirm");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.CANCEL);
        cancelButton.setText("Cancel");

        // Showing the confirmation dialog and waiting for a user response
        alert.showAndWait();

        return alert.getResult() == javafx.scene.control.ButtonType.OK;
    }

    public BorderPane getView() {
        return root;
    }
}