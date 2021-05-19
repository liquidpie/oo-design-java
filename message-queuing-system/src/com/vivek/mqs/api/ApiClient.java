package com.vivek.mqs.api;

import com.google.gson.reflect.TypeToken;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.exception.SystemException;
import com.vivek.mqs.json.JsonHandler;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ApiClient {

    private final String endpoint;

    public ApiClient(String endpoint) {
        this.endpoint = endpoint;
    }

    public Map<String, String> submit(Message message)  {
        try {
            String payload = JsonHandler.serialize(message.getBody());
            return post(payload).join();
        } catch (Exception ex) {
            System.err.println("Error encountered while making request to the endpoint = " + endpoint);
            throw new SystemException(ex.getMessage());
        }
    }

    private CompletableFuture<Map<String, String>> post(String payload) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();

        Type stringMapType = new TypeToken<Map<String, String>>() {}.getType();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> JsonHandler.deserialize(body, stringMapType));
    }

}
