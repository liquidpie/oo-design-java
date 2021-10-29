package com.vivek.external.api;

import com.vivek.external.api.client.ApiClient;
import com.vivek.external.api.model.Response;
import com.vivek.external.api.util.JsonUtil;

import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) {
        ApiClient client = new ApiClient();
        walkFewPages(client, 5);
    }

    private static void walkFewPages(ApiClient client, int numberOfPages) {
        IntStream.range(0, numberOfPages)
                .boxed()
                .map(client::makeRequest)
                .forEach(readResponse());
    }

    private static Consumer<Response> readResponse() {
        return response -> {
            System.out.println("Response:\n<----->");
            JsonUtil.printJson(response);
            System.out.println("\n<----->");
        };
    }

}
