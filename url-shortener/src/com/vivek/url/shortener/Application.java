package com.vivek.url.shortener;

import com.vivek.url.shortener.controller.UrlController;
import com.vivek.url.shortener.converter.UrlConverter;
import com.vivek.url.shortener.dao.AuthDao;
import com.vivek.url.shortener.dao.UrlsDao;
import com.vivek.url.shortener.service.AuthService;
import com.vivek.url.shortener.service.UrlService;

public class Application {

    public static void main(String[] args) {
        // Initializations
        AuthDao authDao = new AuthDao();
        AuthService authService = new AuthService(authDao);
        UrlsDao urlsDao = new UrlsDao();
        UrlConverter urlConverter = new UrlConverter();
        UrlService urlService = new UrlService(urlsDao, urlConverter);
        UrlController controller = new UrlController(authService, urlService);

        // Data
        String userId = "1";

        // User Login
        authService.loginUser(userId);
        // Get Short URL
        String url = controller.getShortUrl("http://www.google.com", userId);
        System.out.println(url);

        // User Logout
        authService.logoutUser(userId);
        // Get Short URL
        String error = controller.getShortUrl("http://www.google.com", userId);
        System.out.println(error);

        System.out.println(urlsDao.getAllUrls());

    }

}
