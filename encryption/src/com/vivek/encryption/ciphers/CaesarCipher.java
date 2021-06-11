package com.vivek.encryption.ciphers;

import com.vivek.encryption.algorithm.StringCipher;

public class CaesarCipher implements StringCipher {

    private final int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    @Override
    public String encrypt(String plainText) {
        StringBuilder builder = new StringBuilder();

        for (char ch : plainText.toCharArray()) {
            char val = Character.isUpperCase(ch)
                    ? (char) ((ch + shift - 65) % 26 + 65)
                    : (char) ((ch + shift - 97) % 26 + 97);
            builder.append(val);
        }
        return builder.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        StringBuilder builder = new StringBuilder();

        for (char ch : cipherText.toCharArray()) {
            char val = Character.isUpperCase(ch)
                    ? (char) ((ch + 26 - shift - 65) % 26 + 65)
                    : (char) ((ch + 26 - shift - 97) % 26 + 97);
            builder.append(val);
        }
        return builder.toString();
    }
}
