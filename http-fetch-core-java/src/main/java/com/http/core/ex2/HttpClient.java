package com.http.core.ex2;

import com.http.core.ex2.model.UserRequest;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://reqres.in/api/users?page=%d";

    private static final String POST_URL = "https://reqres.in/api/users";

    public JSONObject sendGET(int page) {
        try {
            URL obj = new URL(String.format(GET_URL, page));
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println("Raw: " + response);
                return new JSONObject(response.toString());
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public JSONObject sendPOST(String firstName, String lastName) {
        try {
            URL obj = new URL(POST_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);

            // For POST only - START
            UserRequest request = new UserRequest(firstName, lastName);

            String body = JsonUtil.toJson(request);
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
            // For POST only - END

            int responseCode = con.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println("Raw:" + response);
                return new JSONObject(response.toString());
            } else {
                System.out.println("POST request did not work.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
