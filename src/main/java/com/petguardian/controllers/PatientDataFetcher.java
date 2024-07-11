package com.petguardian.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.petguardian.Model.PatientModelClass;
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.DoctorModelClass.AvailableDay;

public class PatientDataFetcher {
        public List<PatientModelClass> fetchPatientData() throws Exception {
        System.out.println("In fetching patient  data ...");
        String apiUrl = "http://localhost:3000/getPatient";
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
        System.out.println("disconnect");

        // Use Gson to parse JSON response
        Gson gson = new Gson();
        JsonObject jsonResponse = gson.fromJson(sb.toString(), JsonObject.class);
        JsonArray patientArray = jsonResponse.getAsJsonArray("data");

        List<PatientModelClass> patientList = new ArrayList<>();

        // Parse availableDays for each doctor
        for (JsonElement patientElement : patientArray) {
            JsonObject patientObject = patientElement.getAsJsonObject();

            String patientId = patientObject.get("patientId").getAsString();
            String name = patientObject.get("name").getAsString();
            String contact = patientObject.get("contact").getAsString();
            String petType = patientObject.get("petType").getAsString();
            String appointmentDay = patientObject.get("appointmentDay").getAsString();
            String appointmentTime = patientObject.get("appointmentTime").getAsString();
            String status = patientObject.get("status").getAsString();
        
            System.out.println(status);
            PatientModelClass patient = new PatientModelClass(
                    patientId, name, contact, petType, appointmentDay, appointmentTime, status
            );
            
            patientList.add(patient);
        }

        // Access and print available days and times for each doctor
        for (PatientModelClass patient : patientList) {
            System.out.println("Patient Name: " + patient.getName()+patient.getAppointmentDay());
            
        }

        return patientList;
    }

    // public static void main(String[] args) {
    //     PatientDataFetcher obj=new PatientDataFetcher();
    //     try {
    //         List<PatientModelClass>x=obj.fetchPatientData();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         System.out.println(e.getMessage());
    //     }
    // }
}
