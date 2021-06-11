package com.vivek.encryption;

import com.vivek.encryption.algorithm.Cipher;
import com.vivek.encryption.ciphers.CaesarCipher;
import com.vivek.encryption.ciphers.XorCipher;

public class AppTest {

    public static void main(String[] args) {
        // XOR Cipher
        String content = "Hello World!";
        String key = "123456";

        Cipher xorCipher = new XorCipher(key);
        // Encrypted Data
        byte[] cipherText = xorCipher.encrypt(content.getBytes());
        // Decrypted Data
        byte[] plainText = xorCipher.decrypt(cipherText);

        System.out.println(new String(plainText));

        // Caesar Cipher
        CaesarCipher caesarCipher = new CaesarCipher(5);
        String plaintext = "ABCDEF";
        String ciphertext = caesarCipher.encrypt(plaintext);
        System.out.println(ciphertext);
        System.out.println(caesarCipher.decrypt(ciphertext));

    }

}
