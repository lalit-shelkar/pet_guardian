package com.petguardian.views.doctor;

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
    private List<String[]> patientsList; // Using a List of String arrays for patient data

    public DoctorDashboard(Pet app) {
        this.app = app;
        this.patientsList = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        // Initialize patients data
        initializePatients();

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

    private void initializePatients() {
        // Add some sample patients
        patientsList.add(new String[]{"John Doe", "123-456-7890", "Dog", "5", "Monday 10:00 AM", "Pending"});
        patientsList.add(new String[]{"Jane Smith", "234-567-8901", "Cat", "3", "Tuesday 2:00 PM", "Pending"});
        patientsList.add(new String[]{"Mary Johnson", "345-678-9012", "Bird", "2", "Wednesday 11:00 AM", "Pending"});
    }

    private void showPatients() {
        GridPane patientsGrid = new GridPane();
        patientsGrid.setPadding(new Insets(15));
        patientsGrid.setHgap(10); // Horizontal gap between columns
        patientsGrid.setVgap(20); // Vertical gap between rows

        // Column constraints for equal width
        for (int i = 0; i < 7; i++) { // Adjusted to 7 columns including S. No.
            ColumnConstraints columnConstraints = new ColumnConstraints();
            columnConstraints.setPercentWidth(100.0 / 7);
            patientsGrid.getColumnConstraints().add(columnConstraints);
        }

        // Add headers to the grid
        addHeader(patientsGrid, "S. No.", 0);
        addHeader(patientsGrid, "Name", 1);
        addHeader(patientsGrid, "Contact", 2);
        addHeader(patientsGrid, "Pet Type", 3);
        addHeader(patientsGrid, "Age", 4);
        addHeader(patientsGrid, "Appointment Day & Time", 5);
        addHeader(patientsGrid, "Status", 6);

        // Add patients dynamically
        for (int i = 0; i < patientsList.size(); i++) {
            String[] patient = patientsList.get(i);

            // S. No. column
            Label serialNumber = new Label(String.valueOf(i + 1));
            serialNumber.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
            patientsGrid.add(serialNumber, 0, i + 1);

            // Name, Contact, Pet Type, Age, Appointment, Status columns
            for (int j = 0; j < patient.length; j++) {
                Label label = new Label(patient[j]);
                label.setStyle("-fx-padding: 5px; -fx-background-color: #ecf0f1; -fx-font-weight: bold;");
                patientsGrid.add(label, j + 1, i + 1);
            }

            // Status column with toggle button
            Button statusButton = new Button(patient[5]); // Assuming status is at index 5
            statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                    (patient[5].equals("Pending") ? "#e67e22;" : "#2ecc71;") + // Orange for Pending, Green for Confirmed
                    "-fx-font-weight: bold; -fx-text-fill: white;");
            int finalI = i; // To use inside lambda expression
            statusButton.setOnAction(event -> {
                // Ask for confirmation
                if (confirmStatusChange(patient[0])) { // Assuming patient name is at index 0
                    // Toggle status
                    patientsList.get(finalI)[5] = patientsList.get(finalI)[5].equals("Pending") ? "Confirmed" : "Pending";
                    // Update button text and background color
                    statusButton.setText(patientsList.get(finalI)[5]);
                    statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                            (patientsList.get(finalI)[5].equals("Pending") ? "#e67e22;" : "#2ecc71;") +
                            "-fx-font-weight: bold; -fx-text-fill: white;");
                }
            });
            patientsGrid.add(statusButton, 6, i + 1);
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
