package com.petguardian.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.petguardian.Model.DoctorModelClass;

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
        List<DoctorModelClass> doctorList = gson.fromJson(jsonResponse.get("data"), new TypeToken<List<DoctorModelClass>>() {
        }.getType());

        return doctorList;
    }
}
