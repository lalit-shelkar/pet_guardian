package com.petguardian.views.doctor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.petguardian.controllers.IsDoctorExist;
import com.petguardian.controllers.Pet;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DoctorGetStarted {
    private Pet app;
    private GridPane grid;
    public DoctorGetStarted(Pet app){
      
        this.app=app;
        initialize();
    }

    private void initialize(){
        boolean flag=false;
        try {
            flag=IsDoctorExist.doctorExist();
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!flag){
            app.navigationToDoctorForm();
        }else{
            System.out.println("doctor dashbord ToDO");
        }
     
      

    }

    
}
