package com.vivek.url.shortener.controller;

import com.vivek.url.shortener.domain.ErrorType;
import com.vivek.url.shortener.domain.UrlShortenRequest;
import com.vivek.url.shortener.domain.UrlShortenResponse;
import com.vivek.url.shortener.service.AuthService;
import com.vivek.url.shortener.service.UrlService;

public class UrlController {

    private final AuthService authService;
    private final UrlService urlService;

    public UrlController(AuthService authService, UrlService urlService) {
        this.authService = authService;
        this.urlService = urlService;
    }

    public UrlShortenResponse getShortUrl(UrlShortenRequest request, String userId) {
        if (!authService.checkIfUserLoggedIn(userId)) {
            return new UrlShortenResponse(ErrorType.USER_NOT_LOGGED_IN);
        }

        return urlService.shortenUrl(request, userId);
    }

    public String getAndRedirect(String shortUrl) {
        return urlService.getOriginalUrl(shortUrl);
    }

}
