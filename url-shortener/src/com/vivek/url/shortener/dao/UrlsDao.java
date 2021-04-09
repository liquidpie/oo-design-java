package com.vivek.url.shortener.dao;

import com.vivek.url.shortener.dto.MappedKey;
import com.vivek.url.shortener.dto.URL;
import com.vivek.url.shortener.persistence.Database;

import java.util.Collection;

public class UrlsDao {

    public void addUrl(URL url) {
        Database.URLS.put(new MappedKey(url.getUserId(), url.getOriginalUrl()), url);
    }

    public URL getUrl(String userId, String originalUrl) {
        return Database.URLS.get(new MappedKey(userId, originalUrl));
    }

    public URL getUrlByHash(String hash) {
        return Database.URLS.values().stream()
                .filter(url -> url.getHash().equals(hash))
                .findFirst()
                .orElse(null);
    }

    public Collection<URL> getAllUrls() {
        return Database.URLS.values();
    }

}
