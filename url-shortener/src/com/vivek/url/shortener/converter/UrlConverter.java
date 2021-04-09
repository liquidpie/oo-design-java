package com.vivek.url.shortener.converter;

import com.vivek.url.shortener.domain.URL;

public class UrlConverter {

    public URL convert(com.vivek.url.shortener.dto.URL url) {
        return new URL(url.getOriginalUrl(), url.getHash(), url.getUserId());
    }
}
