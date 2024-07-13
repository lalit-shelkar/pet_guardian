package com.petguardian.views.doctor;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.petguardian.Exceptions.DayNotAvalable;
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.DoctorModelClass.AvailableDay;
import com.petguardian.Model.PatientModelClass;
import com.petguardian.controllers.DoctorDataFetcher;
import com.petguardian.controllers.PatientDataFetcher;
import com.petguardian.controllers.Pet;
import com.petguardian.firebase.MyAuthentication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class DoctorDashboard {
    private Pet app;
    private BorderPane root;
    private List<PatientModelClass> patientsList;
    private List<PatientModelClass> upcomingPatientsList;
    private List<PatientModelClass> historyPatientsList;
    List<DoctorModelClass> doctorList;
    private Button selectedDayButton = null;
    private Button navSelectedButton = null;

   // private List<DoctorModelClass> doctorList;

    public DoctorDashboard(Pet app) {
        this.app = app;
        this.patientsList = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        // Initialize patients data
        PatientDataFetcher patientDataFetcher = new PatientDataFetcher();
        //DoctorDataFetcher doctorDataFetcher = new DoctorDataFetcher();
        try {
           // doctorList = doctorDataFetcher.fetchDoctorData();
           getDoctor();
            patientsList = patientDataFetcher.fetchPatientData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //calling categorize method
        categorizePatients();
        // Main layout
        root = new BorderPane();

        // Set the left navigation panel in the BorderPane
        root.setLeft(navigationPanel());
        showDashboard();

        // Initially show the list of upcoming patients
        //showUpcomingPatients();
    }

  private VBox navigationPanel() {
    VBox navigationPanel = new VBox();
    navigationPanel.setPrefWidth(300);
    navigationPanel.setSpacing(10); // Spacing between buttons
    navigationPanel.setPadding(new Insets(15));
    navigationPanel.setStyle("-fx-background-color: #002240;-fx-background-radius:20"); // Dark background color

    // Add some buttons to the navigation panel
    ImageView logo = new ImageView(new Image("dashboard/logo.png"));
    logo.setFitHeight(200);
    logo.setFitWidth(200);
    logo.setStyle("-fx-background-color: #2c3e50;");
    logo.setPreserveRatio(true);

    VBox vb = new VBox();
    vb.setPrefHeight(480);
    Button dashboardButton = new Button("Dashboard");
    dashboardButton.setMinWidth(300);
    Button patientsButton = new Button("Patients");
    patientsButton.setMinWidth(300);

    Button upcomingPatientsButton = new Button("Upcoming Patients");
    upcomingPatientsButton.setMinWidth(300);

    Button historyPatientsButton = new Button("History Patients");
    historyPatientsButton.setMinWidth(300);

    Button refreshButton = new Button("Refresh");
    refreshButton.setMinWidth(300);

    Button logOutButton = new Button("Log Out =>");
    logOutButton.setMinWidth(300);


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
    logOutButton.setStyle(buttonStyle + "-fx-text-fill: red;-fx-font-size: 24px");
    upcomingPatientsButton.setStyle(buttonStyle);
    historyPatientsButton.setStyle(buttonStyle);
    refreshButton.setStyle(buttonStyle);

    // Add buttons to the navigation panel
    navigationPanel.getChildren().addAll(logo, dashboardButton, patientsButton,
            upcomingPatientsButton, historyPatientsButton,refreshButton, vb, logOutButton);

    navSelectedButton = dashboardButton;
    navSelectedButton.setStyle("-fx-background-color:#f89095");

    // Method to reset button styles
    EventHandler<ActionEvent> resetButtonStyles = event -> {
        dashboardButton.setStyle(buttonStyle);
        patientsButton.setStyle(buttonStyle);
        upcomingPatientsButton.setStyle(buttonStyle);
        historyPatientsButton.setStyle(buttonStyle);
        refreshButton.setStyle(buttonStyle);
        logOutButton.setStyle(buttonStyle + "-fx-text-fill: red;-fx-font-size: 24px");
    };

    // Event handler for the Dashboard button
    dashboardButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = dashboardButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        showDashboard();
    });

    // Event handler for the Patients button
    patientsButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = patientsButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        showPatients();
    });

    // Event handler for the Upcoming Patients button
    upcomingPatientsButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = upcomingPatientsButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        showUpcomingPatients();
    });

    // Event handler for the History Patients button
    historyPatientsButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = historyPatientsButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        showHistoryPatients();
    });

    refreshButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = dashboardButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        showDashboard();
    PatientDataFetcher patientDataFetcher = new PatientDataFetcher();
    //DoctorDataFetcher doctorDataFetcher = new DoctorDataFetcher();
        
    try {
       // doctorList = doctorDataFetcher.fetchDoctorData();
       getDoctor();
        patientsList = patientDataFetcher.fetchPatientData();
    } catch (Exception e) {
        e.printStackTrace();
    }
    });

    // Event handler for the Log Out button
    logOutButton.setOnAction(event -> {
        resetButtonStyles.handle(event);
        navSelectedButton = logOutButton;
        navSelectedButton.setStyle("-fx-background-color:#f89095");
        app.navigateToLoginView();; // Assuming logOut() method exists
    });

    return navigationPanel;
}


   private void showDashboard() {
   
    //calling categorize method
    categorizePatients();
    VBox dashboardView = new VBox();
    dashboardView.setPadding(new Insets(20));
    dashboardView.setSpacing(90);
    dashboardView.setStyle("-fx-background-color: #ecf0f1;");
    //
   

    //Image Profile
    ImageView profile = new ImageView(new Image(doctorList.get(0).getImg()));
    
        profile.setFitWidth(100);
        profile.setFitHeight(100);
        Circle clip = new Circle(50, 50, 50); // Radius is half 
        profile.setClip(clip);
       // profile.setLayoutX(1500);

        // Apply additional CSS styling
        profile.setStyle(
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 0, 0);" +
            "-fx-border-color: white;" +
            "-fx-border-width: 2px;"
        );

    // Header
    HBox hbtop=new HBox();
    hbtop.setSpacing(20);
    HBox hbspace=new HBox();
    hbspace.setMinWidth(900);
    Label header = new Label("Welcome!\n"+doctorList.get(0).getName());
    header.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;  -fx-text-fill: orange;");
    Label dash = new Label("Dashboard");
    dash.setStyle("-fx-font-size: 45px; -fx-font-weight: bold;  -fx-text-fill: orange;");
    hbtop.getChildren().addAll(dash,hbspace,profile,header);
    dashboardView.getChildren().add(hbtop);

    // Example statistics
    // HBox statsBox = new HBox();
    // statsBox.setSpacing(30);
    // statsBox.setAlignment(Pos.CENTER);

    // Number of upcoming patients
    VBox upcomingPatientsBox = createStatBox("Appointments", String.valueOf(upcomingPatientsList.size()), "#daedff","dashboard/scroll.png");
    //statsBox.getChildren().add(upcomingPatientsBox);

    // Number of history patients
    VBox historyPatientsBox = createStatBox("History Patients", String.valueOf(historyPatientsList.size()), "#dbffe5","dashboard/history.png");
    //statsBox.getChildren().add(historyPatientsBox);

    // Number of total patients
    VBox totalPatientsBox = createStatBox("Total Patients", String.valueOf(patientsList.size()), "#fffacf","dashboard/ppl.png");
    //statsBox.getChildren().add(totalPatientsBox);

    double totalEarnings = calculateTotalEarnings();
    VBox totalEarningsBox = createStatBox("Total Earnings", String.format(Locale.US, "$%.2f", totalEarnings), "#ead9ff","dashboard/salary.png");
    //statsBox.getChildren().add(totalEarningsBox);


    HBox chartsBox = new HBox();
    chartsBox.setSpacing(400);
    chartsBox.setAlignment(Pos.CENTER);
    // Add PieChart
    PieChart patientDistributionChart = createPetTypeChart();
    patientDistributionChart.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-border-radius: 20px; ");
   // chartsBox.getChildren().add(patientDistributionChart);

    
    //statgridpane
    Label analytics=new Label("Analytics Overview");
    analytics.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;  -fx-text-fill: black;");
    GridPane gridPane = new GridPane();
    gridPane.add(analytics, 2, 1);
    gridPane.add(upcomingPatientsBox, 2, 2);
    gridPane.add(historyPatientsBox, 3, 2);
    gridPane.add(totalPatientsBox, 2, 3);
    gridPane.add(totalEarningsBox, 3, 3);
    gridPane.setHgap(25);
    gridPane.setVgap(25);
    gridPane.setStyle("-fx-background-color: white; -fx-background-radius: 20px; -fx-border-radius: 20px; ");
   // gridPane.setAlignment(Pos.CENTER);
   gridPane.setPadding(new Insets(0,30,30,0));
    
    
    chartsBox.getChildren().addAll(gridPane,patientDistributionChart);
     // Add the stats box to the dashboard view
     //.getChildren().add(chartsBox);
    
    // Add the charts box to the dashboard view
    dashboardView.getChildren().add(chartsBox);
    //vbox
    Label schedule=new Label("Next 5 Days Schedule ...");
    schedule.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;  -fx-text-fill: green;");
    VBox vbleft=new VBox();//left
    vbleft.setSpacing(25);
    vbleft.setAlignment(Pos.CENTER);
    vbleft.getChildren().addAll(schedule,createDaysBox());
    


    //dashboardView.getChildren().add(createDaysBox());
   
    dashboardView.getChildren().add(vbleft);

    // Example charts (you can add actual charts here)
    //Label chartPlaceholder = new Label("Charts and Graphs can go here");
    //chartPlaceholder.setStyle("-fx-font-size: 18px; -fx-text-fill: #7f8c8d;");
    //dashboardView.getChildren().add(chartPlaceholder);

    // Set the dashboard view to the center of the root pane
    root.setCenter(dashboardView);
}

//Avaialbility

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

        for (AvailableDay availableDay : doctorList.get(0).getAvailableDays()) {
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
                    showSelectionAlertBox(e1.getMessage());
                }
            }); // Add event handler for green buttons
        } else {
            dayButton.setStyle(
                    "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-size: 16px; -fx-background-radius: 5px; -fx-opacity: 0.5;-fx-border-color: grey");

            dayButton.setOnAction(e -> {
                try {
                    handleButtonClick(dayButton, false);
                } catch (DayNotAvalable e1) {
                    showSelectionAlertBox(e1.getMessage());
                }
            }); // Add event handler for grey buttons
        }
        return dayButton;
    }


    private void handleButtonClick(Button button, boolean isAvailable) throws DayNotAvalable {
        if (isAvailable) {
            if (selectedDayButton != null) {
                button.setStyle(
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
         button.setOnMouseClicked(e->{
            try {

                if(!confirmAvailable(button.getText())){
                       removeDayToDoctor(MyAuthentication.getUserUid(), button.getText());
                       button.setStyle(
                        "-fx-background-color: lightgrey; -fx-text-fill: black; -fx-font-size: 16px; -fx-background-radius: 5px; -fx-opacity: 0.5;-fx-border-color: grey");
    
                }
            } catch (Exception e1) {
                
                e1.printStackTrace();
            }
        });
                    
        } else {
            /////

            button.setOnAction(event -> {
                // Ask for confirmation
                if (confirmStatusChange(button.getText())) {
                    button.setStyle("-fx-background-color: white; -fx-text-fill: green; -fx-font-size: 16px; -fx-background-radius: 5px;-fx-border-color: green;-fx-border-width: 1px;");
                    try {
                        addDayToDoctor( MyAuthentication.getUserUid() , button.getText());
                    } catch (Exception e) {
                      
                        e.printStackTrace();
                    }              
                }
            });
            
        }
    }

    void showSelectionAlertBox(String msg) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Selection Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private  boolean confirmAvailable(String day) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Availabilty Change");
        alert.setContentText("Are you available on  " + day + "?");

        // Customizing buttons text
        Button confirmButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.OK);
        confirmButton.setText("Available");

        Button cancelButton = (Button) alert.getDialogPane().lookupButton(javafx.scene.control.ButtonType.CANCEL);
        cancelButton.setText("Not Available");

        // Showing the confirmation dialog and waiting for a user response
        alert.showAndWait();

        return alert.getResult() == javafx.scene.control.ButtonType.OK;
    }

private PieChart createPetTypeChart() {
    Map<String, Integer> petTypeCount = new HashMap<>();

    // Count occurrences of each pet type
    for (DoctorModelClass doctor : doctorList) {
        for (PatientModelClass patient : patientsList) {
            petTypeCount.put(patient.getPetType().toLowerCase(), petTypeCount.getOrDefault(patient.getPetType().toLowerCase(), 0) + 1);
        }
    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    int totalPatients = patientsList.size();
    for (Map.Entry<String, Integer> entry : petTypeCount.entrySet()) {
        PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
        pieChartData.add(slice);
    }

    PieChart petTypeChart = new PieChart(pieChartData);
    petTypeChart.setTitle("Pet Distribution");
    petTypeChart.setLabelsVisible(true);
    petTypeChart.setLegendVisible(false);

    // Add tooltips to display the percentage
    for (PieChart.Data data : petTypeChart.getData()) {
        double percentage = (data.getPieValue() / totalPatients) * 100;
        Tooltip tooltip = new Tooltip(data.getName() + ": " + String.format("%.2f%%", percentage));
        Tooltip.install(data.getNode(), tooltip);

        // Update label to show the percentage
        data.setName(data.getName() + ": " + String.format("%.2f%%", percentage));

    }

    return petTypeChart;
}

private double calculateTotalEarnings() {
    double totalEarnings = 0.0;
    if (!doctorList.isEmpty()) {
        double price = doctorList.get(0).getPrice(); // Assuming single doctor in the list
        totalEarnings = historyPatientsList.size() * price;
    }
    return totalEarnings;
}
private VBox createStatBox(String title, String value, String color,String url) {
    VBox statBox = new VBox();

    ImageView iv=new ImageView(new Image(url));
    iv.setFitHeight(50);
    iv.setFitWidth(50);
    iv.setPreserveRatio(true);
    statBox.setPadding(new Insets(15));
    statBox.setSpacing(15);
    statBox.setAlignment(Pos.CENTER);
    statBox.setStyle(" -fx-background-radius: 10px; -fx-border-radius: 10px; "
            + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 5);"+"-fx-background-color:"+color);

    Label titleLabel = new Label(title);
    titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: " + "black" + ";");

    Label valueLabel = new Label(value);
    valueLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: " + "black"+ ";");

    statBox.getChildren().addAll(iv,titleLabel, valueLabel);
    statBox.setPrefSize(200, 200);
    statBox.setMinSize(200,200);
    return statBox;
}

    private void categorizePatients() {
    

    // Initialize lists
    upcomingPatientsList = new ArrayList<>();
    historyPatientsList = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Current date and time for comparison
    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    System.out.println(currentTime);

    for (PatientModelClass patient : patientsList) {
        // Parse appointment date
        String aptDay= patient.getAppointmentDay();
        String arr[]= aptDay.split("-");
        String month=monthfinder(arr[0]);
        System.out.println(month);
        int currentYear = LocalDate.now().getYear();
        String currentYearString = String.valueOf(currentYear); 
       // System.out.println("");
        System.out.println("datet="+currentYearString+"-"+month+"-"+arr[1]);

        String trimDate=currentYearString+"-"+month+"-"+arr[1];
      
        LocalDate appointmentDate = LocalDate.parse(trimDate, formatter);
        System.out.println("for="+appointmentDate);
        String status=patient.getStatus();

       // Compare dates and times&& LocalTime.parse(patient.getCreatedAt()).isAfter(currentTime)
        if ( status.equals("pending") && (appointmentDate.isAfter(currentDate) ||
                (appointmentDate.isEqual(currentDate) ))) {
            upcomingPatientsList.add(patient);
        } else {
            historyPatientsList.add(patient);
        }
    }

}

    private String monthfinder(String month){
        String num="00";
        switch (month) {
            case "July":
                num="07";
                break;
        
            default:
                break;
        }
        return num;
    }

    
    private void showPatients(){
        root.setCenter((createPatientsGrid(patientsList, "Patients")));
    }
    private void showUpcomingPatients() {
        root.setCenter(createPatientsGrid(upcomingPatientsList, "Upcoming Patients"));
    }

    private void showHistoryPatients() {
        root.setCenter(createPatientsGrid(historyPatientsList, "History Patients"));
    }

    private GridPane createPatientsGrid(List<PatientModelClass> patients, String title) {
        GridPane patientsGrid = new GridPane();
        patientsGrid.setPadding(new Insets(15));
        patientsGrid.setHgap(5); // Horizontal gap between columns
        patientsGrid.setVgap(40); // Vertical gap between rows

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
        for (int i = 0; i < patients.size(); i++) {
            PatientModelClass patient = patients.get(i);
            String style="-fx-padding: 25px;  -fx-font-weight: bold; -fx-border-color: transparent; -fx-border-width: 0 0 2 0; -fx-border-color: green;";
            // S. No. column
            Label serialNumber = new Label(String.valueOf(i + 1));
            serialNumber.setStyle(style);
            patientsGrid.add(serialNumber, 0, i + 1);

            // Name column
            Label nameLabel = new Label(patient.getName());
            nameLabel.setStyle(style);
            patientsGrid.add(nameLabel, 1, i + 1);

            // Contact column
            Label contactLabel = new Label(patient.getContact());
            contactLabel.setStyle(style);
            patientsGrid.add(contactLabel, 2, i + 1);

            // Pet Name column
            Label petNameLabel = new Label(patient.getPetName());
            petNameLabel.setStyle(style);
            patientsGrid.add(petNameLabel, 3, i + 1);

            // Pet Type column
            Label petTypeLabel = new Label(patient.getPetType());
            petTypeLabel.setStyle(style);
            patientsGrid.add(petTypeLabel, 4, i + 1);

            // Pet Age column
            Label petAgeLabel = new Label(Integer.toString(patient.getPetAge()));
            petAgeLabel.setStyle(style);
            patientsGrid.add(petAgeLabel, 5, i + 1);

            // Symptoms column
            Label symptomsLabel = new Label(patient.getsymptoms());
            symptomsLabel.setStyle(style);
            patientsGrid.add(symptomsLabel, 6, i + 1);

            // Appointment Day & Time columns
            Label appointmentDay = new Label(patient.getAppointmentDay());
            appointmentDay.setStyle(style);
            patientsGrid.add(appointmentDay, 7, i + 1);

            Label appointmentTime = new Label(patient.getAppointmentTime());
            appointmentTime.setStyle(style);
            patientsGrid.add(appointmentTime, 8, i + 1);

            // Status column with toggle button
            Button statusButton = new Button(patient.getStatus());
            statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                    (patient.getStatus().equals("pending") ? "#e67e22;" : "#2ecc71;") + // Orange for Pending, Green for Confirmed
                    "-fx-font-weight: bold; -fx-text-fill: white;");
            int finalI = i; // To use inside lambda expression
            statusButton.setOnAction(event -> {
                // Ask for confirmation
                if (confirmStatusChange(patient.getName())) {
                    // Toggle status
                    patients.get(finalI).setStatus(patients.get(finalI).getStatus().equals("pending") ? "confirmed" : "pending");
                    // Update button text and background color
                    statusButton.setText(patients.get(finalI).getStatus());
                    statusButton.setStyle("-fx-padding: 5px; -fx-background-color: " +
                            (patients.get(finalI).getStatus().equals("Pending") ? "#e67e22;" : "#2ecc71;") +
                            "-fx-font-weight: bold; -fx-text-fill: white;");
                    addToHistory(patient);
                    showUpcomingPatients();

                    try {
                        toggleStatus(patient.get_Id());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    
                    
                }
            });
            patientsGrid.add(statusButton, 9, i + 1);
        }

        return patientsGrid;
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

    private void addToHistory(PatientModelClass patient){
        upcomingPatientsList.remove(upcomingPatientsList.indexOf(patient));
        historyPatientsList.add(0, patient);
    }

    private void toggleStatus(String id) throws Exception{
        String apiUrl = "https://pet-api-two.vercel.app/changeStatus?_id="+id; //+ MyAuthentication.getUserUid();
        URL url = new URL(apiUrl);
        System.out.println("url");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        System.out.println("conn");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        conn.disconnect();
    }
    public BorderPane getView() {
        return root;
    }

    private void getDoctor() throws Exception{
        System.out.println("In fetching doctor data ...");
        String apiUrl = "https://pet-api-two.vercel.app/getDoctorById?doctorId="+MyAuthentication.getUserUid();
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        conn.disconnect();

        // Use Gson to parse JSON response
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(sb.toString(), JsonObject.class);
        JsonArray doctorArray = jsonResponse.getAsJsonArray("data");

        doctorList = new ArrayList<>();

        // Parse availableDays for each doctor
        for (JsonElement doctorElement : doctorArray) {
            JsonObject doctorObject = doctorElement.getAsJsonObject();

            String firestoreId = doctorObject.get("firestoreId").getAsString();
            String name = doctorObject.get("name").getAsString();
            String experience = doctorObject.get("experience").getAsString();
            String qualification = doctorObject.get("qualification").getAsString();
            double rating = doctorObject.get("rating").getAsDouble();
            String img = doctorObject.get("img").getAsString();
            Set<String> tags = gson.fromJson(doctorObject.get("tags"), new TypeToken<Set<String>>() {
            }.getType());
            String about = doctorObject.get("about").getAsString();
            String location = doctorObject.get("location").getAsString();
            String specializes = doctorObject.get("specializes").getAsString();
            String contact = doctorObject.get("contact").getAsString();
            double price = doctorObject.get("price").getAsDouble();
            boolean available = doctorObject.get("available").getAsBoolean();
            JsonArray availableDaysArray = doctorObject.getAsJsonArray("availableDays");

            Set<AvailableDay> availableDays = new HashSet<>();

            for (JsonElement dayElement : availableDaysArray) {
                JsonArray dayArray = dayElement.getAsJsonArray();
                String date = dayArray.get(0).getAsString();
                List<String> times = gson.fromJson(dayArray.get(1), new TypeToken<List<String>>() {
                }.getType());
                availableDays.add(new AvailableDay(date, times));
            }

            DoctorModelClass doctor = new DoctorModelClass(
                    firestoreId, name, qualification, experience, rating, img, tags, about, location,
                    available, price, specializes, contact, availableDays, new HashSet<>());

            doctorList.add(doctor);
        }
    }

    private void addDayToDoctor(String id,String day) throws Exception{
        String apiUrl = "https://pet-api-two.vercel.app/addDay?doctorId="+id+"&day="+day; //+ MyAuthentication.getUserUid();
        URL url = new URL(apiUrl);
        System.out.println("url");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        System.out.println("conn");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        conn.disconnect();
    }

    private void removeDayToDoctor(String id,String day) throws Exception{
        System.err.println("in remove");
        String apiUrl = "https://pet-api-two.vercel.app/removeDay?doctorId="+id+"&day="+day; //+ MyAuthentication.getUserUid();
        URL url = new URL(apiUrl);
        System.out.println("url");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        System.out.println("conn");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            sb.append(output);
        }

        conn.disconnect();
    }
}
