package com.http.core.ex1.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private GsonUtil() { }

    public static <T> T fromJson(String input, Class<T> clazz) {
        return gson.fromJson(input, clazz);
    }

    public static <T> void printJson(T input) {
        String json = gson.toJson(input);
        System.out.println(json);
    }

}
