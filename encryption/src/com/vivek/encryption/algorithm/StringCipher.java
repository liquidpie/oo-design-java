package com.vivek.encryption.algorithm;

public interface StringCipher extends Cipher {

    String encrypt(String plainText);

    String decrypt(String cipherText);

    @Override
    default byte[] encrypt(byte[] data) {
        throw new UnsupportedOperationException();
    }

    @Override
    default byte[] decrypt(byte[] data) {
        throw new UnsupportedOperationException();
    }

}
