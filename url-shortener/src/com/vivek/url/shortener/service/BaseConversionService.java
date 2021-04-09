package com.vivek.url.shortener.service;

public class BaseConversionService {

    private static final String base62alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] characterMap = base62alphabet.toCharArray();
    private final int base = characterMap.length;

    public String encode(long input) {
        var encodedString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(characterMap[0]);
        }

        while (input > 0) {
            encodedString.append(characterMap[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    public long decode(String input) {
        var characters = input.toCharArray();
        var length = characters.length;

        var decoded = 0;

        var counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += base62alphabet.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }

        return decoded;
    }

}
