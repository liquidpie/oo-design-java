package com.vivek.encryption;

import com.vivek.encryption.algorithm.Crypt;
import com.vivek.encryption.algorithm.XorCrypt;

public class AppTest {

    public static void main(String[] args) {
        String content = "Hello World!";
        String key = "123456";

        Crypt xorCrypt = new XorCrypt(key);
        // Encrypted Data
        byte[] cipherText = xorCrypt.encrypt(content.getBytes());
        // Decrypted Data
        byte[] plainText = xorCrypt.decrypt(cipherText);

        System.out.println(new String(plainText));

    }

}
