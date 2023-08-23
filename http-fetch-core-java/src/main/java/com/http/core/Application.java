package com.http.core;

import com.http.core.ex1.utils.ApiClient;
import com.http.core.ex1.utils.GsonUtil;
import com.http.core.ex2.HttpClient;
import com.http.core.ex1.model.Response;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) throws IOException {
        {
            ApiClient client = new ApiClient();
            walkFewPages(client, 5);
        }

        {
            HttpClient client = new HttpClient();
            client.sendGET();
            System.out.println("GET DONE");
            client.sendPOST();
            System.out.println("POST DONE");
        }
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
            GsonUtil.printJson(response);
            System.out.println("\n<----->");
        };
    }

}
