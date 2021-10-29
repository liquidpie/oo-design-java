package com.vivek.external.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() { }

    public static <T> T fromJson(String input, Class<T> clazz) {
        return gson.fromJson(input, clazz);
    }

    public static <T> void printJson(T input) {
        String json = gson.toJson(input);
        System.out.println(json);
    }

}
