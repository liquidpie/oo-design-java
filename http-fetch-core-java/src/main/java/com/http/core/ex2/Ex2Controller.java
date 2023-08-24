package com.http.core.ex2;

import com.http.core.ex2.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.codevoila.com/post/65/java-json-tutorial-and-example-json-java-orgjson
 *
 * https://www.tutorialspoint.com/json_simple/json_simple_quick_guide.htm
 *
 * https://www.baeldung.com/java-org-json
 */
public class Ex2Controller {

    private final HttpClient client;

    public Ex2Controller() {
        this.client = new HttpClient();
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        int totalPages = 0;
        int page = 1;
        do {
            JSONObject jsonObject = client.sendGET(page);
            totalPages = jsonObject.getInt("total_pages");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                User user = (new User.Builder())
                                        .withId(obj.getInt("id"))
                                        .withFirstName(obj.getString("first_name"))
                                        .withLastName(obj.getString("last_name"))
                                        .withEmail(obj.getString("email"))
                                        .withAvatar(obj.getString("avatar"))
                                        .build();

                users.add(user);
            }
            page++;
        } while (page <= totalPages);

        return users;
    }

    public User createUser(String firstName, String lastName) {
        JSONObject jsonObject = client.sendPOST(firstName, lastName);
        if (jsonObject != null) {
            User.Builder userBuilder = new User.Builder();
            return userBuilder.withId(jsonObject.getInt("id"))
                    .build();
        }
        return null;
    }

}
