package com.vivek.mqs.json;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonHandler {

    private static final Gson gson = new Gson();

    public static <T> String serialize(T message) {
        return gson.toJson(message);
    }

    public static <T> T deserialize(String payload, Class<T> clazz) {
        return gson.fromJson(payload, clazz);
    }

    public static <T> T deserialize(String payload, Type type) {
        return gson.fromJson(payload, type);
    }

}
