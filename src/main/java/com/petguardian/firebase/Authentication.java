package com.petguardian.firebase;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;

import java.net.URL;
import org.json.JSONObject;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Authentication {

    public static String LoginUser(String email, String password) {

        try {
            String apiKey = "AIzaSyAVII9UNVD9Y0NNjYDGuJLUdwVYuKKcVxs";
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("email", email);
            jsonRequest.put("password", password);
            jsonRequest.put("returnSecureToken", true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonRequest.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // Read the response
                try (InputStream is = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    // System.out.println("Successful login");
                    // System.out.println("Response: " + response.toString());
                    return "success";
                }
            } else {
                // Read the error response
                try (InputStream is = conn.getErrorStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Error during login: " + response.toString());
                    return response.toString();
                }
            }
        } catch (Exception e) {
            System.err.println("Error in LoginUser");
            e.printStackTrace();
            return e.toString();
        }
    }

    public static String RegisterUser(String email, String password) {

        System.err.println(email);
        System.err.println(password);
        try {

            String apiKey = "AIzaSyAVII9UNVD9Y0NNjYDGuJLUdwVYuKKcVxs";
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            JSONObject jsonRequest = new JSONObject();
            jsonRequest.put("email", email);
            jsonRequest.put("password", password);
            jsonRequest.put("returnSecureToken", true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonRequest.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            if (conn.getResponseCode() == 200) {
                System.out.println("Successful registration");
                return "success";
            } else {
                System.out.println("Error during registration");

                return conn.getResponseMessage();
            }
        } catch (Exception e) {
            System.err.println("Error in RegisterUser");
            e.printStackTrace();
            return e.toString();
        }

    }
}
