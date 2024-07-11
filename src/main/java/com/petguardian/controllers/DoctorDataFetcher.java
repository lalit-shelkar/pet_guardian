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
import com.petguardian.Model.DoctorModelClass;
import com.petguardian.Model.DoctorModelClass.AvailableDay;

public class DoctorDataFetcher {

    public List<DoctorModelClass> fetchDoctorData() throws Exception {
        System.out.println("In fetching doctor data ...");
        String apiUrl = "https://pet-api-two.vercel.app/getDoctor";
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

        List<DoctorModelClass> doctorList = new ArrayList<>();

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

        return doctorList;
    }

}
