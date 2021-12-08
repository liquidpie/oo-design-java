package com.vivek.kvstore;

import com.vivek.interview.client.KeyValueClient;
import com.vivek.interview.controller.KeyValueController;
import com.vivek.interview.model.Value;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeyValueStoreApp {

    public static void main(String[] args) {
        KeyValueController controller = new KeyValueController();

        // Add a new key value
        String key = "BBSR";
        List<Value> values = KeyValueClient.valueBuilder().addAttribute("temperature", 20.0).addAttribute("latitude", "20.5").build();
        controller.put(key, values);

        // Get a key
        Map<String, Object> result = controller.get(key);
        System.out.println(result);

        // Delete a key
        controller.delete(key);
        Set<String> keys = controller.allKeys();
        System.out.println(!keys.contains(key));

        // Add a secondary index
        String secondaryIndexKey = "population_level";
        controller.addSecondaryIndex(secondaryIndexKey);


    }

}
