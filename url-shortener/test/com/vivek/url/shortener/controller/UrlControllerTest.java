package com.vivek.url.shortener.controller;

import com.vivek.url.shortener.converter.UrlConverter;
import com.vivek.url.shortener.dao.AuthDao;
import com.vivek.url.shortener.dao.UrlsDao;
import com.vivek.url.shortener.config.AppProperties;
import com.vivek.url.shortener.service.AuthService;
import com.vivek.url.shortener.service.UrlService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlControllerTest {

    private AuthService authService;
    private UrlController urlController;

    @BeforeEach
    void setUp() {
        AuthDao authDao = new AuthDao();
        authService = new AuthService(authDao);
        UrlsDao urlsDao = new UrlsDao();
        UrlConverter urlConverter = new UrlConverter();
        UrlService urlService = new UrlService(urlsDao, urlConverter);
        urlController = new UrlController(authService, urlService);
    }

    @Test
    public void shouldReturnShortenedUrl() {
        String userId = "1";
        String url = "www.facebook.com";
        // User Login
        authService.loginUser(userId);

        String expected = AppProperties.DOMAIN + "310513826";
        String actual = urlController.getShortUrl(url, userId);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnErrorWhenUserIsNotLoggedIn() {
        String userId = "1";
        String url = "www.facebook.com";
        // remove session if user
        authService.logoutUser(userId);
        String err = "User not logged in";
        String actual = urlController.getShortUrl(url, userId);

        Assertions.assertEquals(err, actual);
    }

    @Test
    public void shouldReturnShortenedUrlForMultipleUsers() {
        String userId = "1";
        String url = "www.facebook.com";
        String userId2 = "2";
        // User Login
        authService.loginUser(userId);
        authService.loginUser(userId2);

        String url1 = urlController.getShortUrl(url, userId);
        String url2 = urlController.getShortUrl(url, userId2);

        Assertions.assertNotEquals(url1, url2);
    }

}
