package com.http.core.ex2;

import org.json.JSONObject;

public class JsonUtil {

    public static String toJson(Object object) {
        JSONObject jsonObject = new JSONObject(object);
        return jsonObject.toString();
    }

}
