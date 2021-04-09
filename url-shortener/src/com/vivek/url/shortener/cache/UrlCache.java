package com.vivek.url.shortener.cache;

import com.vivek.url.shortener.dto.MappedKey;

import java.util.HashMap;
import java.util.Map;

public class UrlCache {

    private static final Map<MappedKey, String> LONG_TO_SHORT_URL = new HashMap<>(100);

    public String getShortUrl(String longUrl, String userId) {
        MappedKey cacheKey = new MappedKey(userId, longUrl);
        return LONG_TO_SHORT_URL.get(cacheKey);
    }

    public void addShortUrl(String longUrl, String userId, String shortUrl) {
        MappedKey cacheKey = new MappedKey(userId, longUrl);
        LONG_TO_SHORT_URL.put(cacheKey, shortUrl);
    }

}
