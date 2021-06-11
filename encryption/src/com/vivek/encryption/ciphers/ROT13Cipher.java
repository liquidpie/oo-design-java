package com.vivek.encryption.ciphers;

import com.vivek.encryption.algorithm.StringCipher;

public class ROT13Cipher implements StringCipher {

    private final CaesarCipher cipher = new CaesarCipher(13);

    @Override
    public String encrypt(String plainText) {
        return cipher.encrypt(plainText);
    }

    @Override
    public String decrypt(String cipherText) {
        return cipher.decrypt(cipherText);
    }
}
