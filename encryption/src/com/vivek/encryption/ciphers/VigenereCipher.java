package com.vivek.encryption.ciphers;

import com.vivek.encryption.algorithm.StringCipher;

public class VigenereCipher implements StringCipher {

    private final String key;

    public VigenereCipher(String key) {
        this.key = key.toUpperCase();
    }

    @Override
    public String encrypt(String plainText) {
        plainText = plainText.toUpperCase();
        String keyword = repeatKey(plainText.length());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char ch = (char) ((plainText.charAt(i) + keyword.charAt(i)) % 26 + 'A');
            builder.append(ch);
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        String keyword = repeatKey(cipherText.length());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = (char) ((cipherText.charAt(i) - keyword.charAt(i) + 26) % 26 + 'A');
            builder.append(ch);
        }
        return builder.toString();
    }

    private String repeatKey(int n) {
        StringBuilder builder = new StringBuilder();
        while (n >= key.length()) {
            builder.append(key);
            n -= key.length();
        }

        builder.append(key.substring(n));

        return builder.toString();
    }

}
