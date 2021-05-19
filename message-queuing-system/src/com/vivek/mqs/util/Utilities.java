package com.vivek.mqs.util;

import java.util.UUID;

public final class Utilities {

    private Utilities() { }

    public static boolean isNotBlank(String s) {
        return s != null && !s.isBlank();
    }

    public static boolean isBlank(String s) {
        return s == null || s.isBlank();
    }

    public static String newUUID() {
        return UUID.randomUUID().toString();
    }

}
