package com.vivek.bnpl.util;

import java.util.UUID;

public class IdGenerator {

    public static String newId() {
        return UUID.randomUUID().toString();
    }

}
