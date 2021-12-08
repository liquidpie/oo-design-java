package com.vivek.url.shortener.service;

import com.vivek.url.shortener.cache.UrlCache;
import com.vivek.url.shortener.config.AppProperties;
import com.vivek.url.shortener.converter.UrlConverter;
import com.vivek.url.shortener.dao.UrlsDao;
import com.vivek.url.shortener.domain.UrlShortenRequest;
import com.vivek.url.shortener.domain.UrlShortenResponse;
import com.vivek.url.shortener.dto.URL;
import com.vivek.url.shortener.validators.UrlValidator;

import java.time.LocalDate;
import java.util.Objects;

public class UrlService {

    private final UrlsDao urlsDao;
    private final UrlCache urlCache;
    private final UrlConverter urlConverter;

    public UrlService(UrlsDao urlsDao, UrlCache urlCache, UrlConverter urlConverter) {
        this.urlsDao = urlsDao;
        this.urlCache = urlCache;
        this.urlConverter = urlConverter;
    }

    public UrlShortenResponse shortenUrl(UrlShortenRequest request, String userId) {

        String originalUrl = UrlValidator.sanitizeUrl(request.getLongUrl());

        String hash = null;

        // cache lookup
        hash = urlCache.getShortUrl(request.getLongUrl(), userId);
        if (hash == null) {
            URL dtoUrl = getOrCreate(request, userId);
            hash = urlConverter.convert(dtoUrl).getHash();
            urlCache.addShortUrl(request.getLongUrl(), userId, hash);
        }

        return new UrlShortenResponse(AppProperties.DOMAIN + hash);
    }

    public String getOriginalUrl(String shortUrl) {
        URL url = urlsDao.getUrlByHash(shortUrl);
        if (url.getExpirationDate() != null && url.getExpirationDate().isBefore(LocalDate.now())) {
            urlsDao.delete(url.getHash());
            return "Link Expired";
        }
        return url.getOriginalUrl();
    }

    private URL getOrCreate(UrlShortenRequest request, String userId) {
        URL url = urlsDao.getUrl(userId, request.getLongUrl());
        if (url == null) {
            String hash = encode(request.getLongUrl(), userId);
            url = new URL(hash, request.getLongUrl(), LocalDate.now(), request.getExpiresDate(), userId);
            urlsDao.addUrl(url);
        }
        return url;
    }

    private String encode(String url, String userId) {
        return String.valueOf(Objects.hash(url, userId));
    }

}
