package com.vivek.url.shortener.converter;


import com.vivek.url.shortener.domain.User;

public class UserConverter {

    public User convert(com.vivek.url.shortener.dto.User user) {
        return new User(user.getId(), user.getName());
    }

}
