package com.http.core;

import com.http.core.ex1.utils.ApiClient;
import com.http.core.ex1.utils.GsonUtil;
import com.http.core.ex2.Ex2Controller;
import com.http.core.ex2.HttpClient;
import com.http.core.ex1.model.Response;
import com.http.core.ex2.model.User;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) throws IOException {
        {
            ApiClient client = new ApiClient();
            walkFewPages(client, 5);
        }

        {
            Ex2Controller controller = new Ex2Controller();
            List<User> users = controller.getAllUsers();
            System.out.println(users);

            User user = controller.createUser("Kate", "Middleton");
            System.out.println(user);
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
