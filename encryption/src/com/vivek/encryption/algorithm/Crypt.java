package com.vivek.encryption.algorithm;

public interface Crypt {

    byte[] encrypt(byte[] data);

    byte[] decrypt(byte[] data);

}
