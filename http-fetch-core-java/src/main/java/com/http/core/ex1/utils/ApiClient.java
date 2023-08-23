package com.http.core.ex1.utils;

import com.http.core.ex1.model.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * This client uses java's core api to make http requests
 */
public class ApiClient {

    private static final String ENDPOINT = "https://api.instantwebtools.net/v1/passenger?page=%d&size=%d";
    private static final int DEFAULT_PAGE_SIZE = 50;

    private final int pageSize;

    public ApiClient() {
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public ApiClient(int pageSize) {
        this.pageSize = pageSize;
    }

    public Response makeRequest(int pageNumber) {
        String endpoint = String.format(ENDPOINT, pageNumber, pageSize);
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream stream = connection.getInputStream();

            String out = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            return GsonUtil.fromJson(out, Response.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
