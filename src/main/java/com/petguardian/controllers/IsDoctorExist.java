package com.petguardian.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class IsDoctorExist {

    public static boolean doctorExist(String id) throws Exception {
        System.out.println("In IsDoctorExist data ...");
        String apiUrl = "https://pet-api-two.vercel.app/isDoctorExist";
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("firestoreId", id);

        OutputStream os = conn.getOutputStream();
        os.write(requestBody.toString().getBytes());
        os.flush();
        os.close();
        

        if (conn.getResponseCode() == 400) {
            return true;
        } else {
            return false;
        }

        /*
         * BufferedReader in = new BufferedReader(new
         * InputStreamReader(conn.getInputStream()));
         * String inputLine;
         * StringBuffer response = new StringBuffer();
         * 
         * while ((inputLine = in.readLine()) != null) {
         * response.append(inputLine);
         * }
         * in.close();
         * JsonElement jsonResponse = JsonParser.parseString(response.toString());
         * if (jsonResponse.isJsonObject()) {
         * System.out.println("Response is a JSON object");
         * 
         * JsonObject jsonObject = jsonResponse.getAsJsonObject();
         * if (jsonObject.has("flag")) {
         * boolean flag = jsonObject.get("flag").getAsBoolean();
         * return flag;
         * } else {
         * System.out.println("JSON response does not contain 'flag' key");
         * }
         * } else {
         * System.out.println("Response is not a JSON object");
         * return false;
         * 
         * }
         */
        // return false;

    }
}
