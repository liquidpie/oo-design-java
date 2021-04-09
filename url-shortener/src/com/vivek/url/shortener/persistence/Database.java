package com.vivek.url.shortener.persistence;

import com.vivek.url.shortener.dto.MappedKey;
import com.vivek.url.shortener.dto.URL;
import com.vivek.url.shortener.dto.User;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public final class Database {

    private Database() { }

    public static final Map<String, User> USERS = new HashMap<>();
    public static final Map<MappedKey, URL> URLS = new HashMap<>();
    public static final Map<String, Timestamp> SESSIONS = new HashMap<>();

}
