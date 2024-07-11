package com.petguardian.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.petguardian.Model.ProductModelClass;

public class ProductDataFetch {
    public List<ProductModelClass> productList;

    public ProductDataFetch() {
        try {
            productList = fetchProductData();
        } catch (Exception e) {
            System.err.println("exception in fetching product data");
            e.printStackTrace();
        }
    }

    private List<ProductModelClass> fetchProductData() throws Exception {
        System.out.println("In fetching product data ...");
        String apiUrl = "https://pet-api-two.vercel.app/getProduct";
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
        // Parse the JSON response to a map and extract the products array
        ProductResponse response = gson.fromJson(sb.toString(), ProductResponse.class);
        return response.getData();
    }

    ///
    // Inner class to represent the JSON response structure
    private static class ProductResponse {
        private List<ProductModelClass> data;

        public List<ProductModelClass> getData() {
            return data;
        }

        public void setData(List<ProductModelClass> data) {
            this.data = data;
        }
    }
}
