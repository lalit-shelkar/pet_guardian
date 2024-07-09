package com.petguardian.firebase;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.net.URL;
import org.json.JSONObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class MyAuthentication {
    static String uid = "ZDcNUqrKZgfyBiZEdQA747805nn2";

    public static String LoginUser(String email, String password) {
        String result;

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

                    JSONObject jsonResponse = new JSONObject(response.toString());
                    ///
                 
                    setUserUid(jsonResponse.getString("localId"));

                    // Check account type in Firestore
                    Firestore db = FirestoreClient.getFirestore();
                    DocumentReference docRef = db.collection("users").document(getUserUid());
                    ApiFuture<DocumentSnapshot> future = docRef.get();
                    DocumentSnapshot document = future.get();

                    if (document.exists()) {
                        String accountType = document.getString("accountType");
                        if ("user".equalsIgnoreCase(accountType)) {
                            result = "user";
                        } else if ("doctor".equalsIgnoreCase(accountType)) {
                            result = "doctor";
                        } else {
                            result = "Access denied: not a user";
                        }
                    } else {
                        result = "No user data found in Firestore";
                    }
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
                    result = response.toString();
                }
            }
        } catch (Exception e) {
            System.err.println("Error in LoginUser");
            e.printStackTrace();
            result = e.toString();
        }

        return result;
    }

    /// register user
    //
    public static String RegisterUser(String email, String password, String userName, String phone, String role) {
        System.out.println("in register user");
        String result;

        if (phone.trim().isEmpty() || userName.trim().isEmpty() || password.trim().isEmpty()
                || email.trim().isEmpty()) {
            return "Enter valid credential";
        }

        try {
            // Initialize Firestore
            Firestore db = FirestoreClient.getFirestore();

            // Check if the user already exists in Firestore
            QuerySnapshot querySnapshot = db.collection("users").whereEqualTo("email", email).get().get();
            if (!querySnapshot.isEmpty()) {
                return "User already exists";
            }

            // Register the user with Firebase Authentication
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setEmailVerified(false)
                    .setDisabled(false);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            
            setUserUid(userRecord.getUid());

            // Create user data map
            Map<String, Object> user = new HashMap<>();
            user.put("email", email);
            user.put("userName", userName);
            user.put("phone", phone);
            user.put("uid", uid);
            user.put("createdAt", System.currentTimeMillis());
            user.put("accountType", role);

            // Store user details in Firestore
            DocumentReference docRef = db.collection("users").document(uid);
            docRef.set(user).get();
            result = role;
        } catch (FirebaseAuthException e) {
            System.err.println("Error in Firebase Authentication");
            e.printStackTrace();
            result = "Authentication error: " + e.getMessage();
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error in Firestore");
            e.printStackTrace();
            result = "Firestore error: " + e.getMessage();
        }
        return result;
    }

    /// getter setter
    public static void setUserUid(String useruid) {

        MyAuthentication.uid = useruid;
       

    }

    public static String getUserUid() {

        return MyAuthentication.uid;
    }

    /// get user data from any project
    /// this method use in overall project
    public static Map<String, Object> getUserInfo() {

        if (getUserUid() == null) {
            return null;
        }
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("users").whereEqualTo("uid",
                getUserUid()).get();

        try {
            QuerySnapshot querySnapshot = future.get();
            if (!querySnapshot.isEmpty()) {
                // Assuming uid is unique, return the first result
                QueryDocumentSnapshot document = querySnapshot.getDocuments().get(0);
                return document.getData();
            } else {
                System.out.println("No such user found!");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }

    }
}
