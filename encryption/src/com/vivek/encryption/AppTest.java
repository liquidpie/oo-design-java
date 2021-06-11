package com.vivek.encryption;

import com.vivek.encryption.algorithm.Cipher;
import com.vivek.encryption.ciphers.CaesarCipher;
import com.vivek.encryption.ciphers.HillCipher;
import com.vivek.encryption.ciphers.VigenereCipher;
import com.vivek.encryption.ciphers.XorCipher;

public class AppTest {

    public static void main(String[] args) {
        // XOR Cipher
        {
            String content = "Hello World!";
            String key = "123456";

            Cipher xorCipher = new XorCipher(key);
            byte[] cipherText = xorCipher.encrypt(content.getBytes());
            byte[] plainText = xorCipher.decrypt(cipherText);
            System.out.println(new String(plainText));
        }

        // Caesar Cipher
        {
            CaesarCipher caesarCipher = new CaesarCipher(5);
            String plaintext = "ABCDEF";
            String ciphertext = caesarCipher.encrypt(plaintext);
            System.out.println(ciphertext);
            System.out.println(caesarCipher.decrypt(ciphertext));
        }

        // Hill Cipher
        {
            HillCipher hillCipher = new HillCipher("GYBNQKURP");
            String plainText = "ACT";
            String cipherText = hillCipher.encrypt(plainText);
            System.out.println(cipherText);
            plainText = hillCipher.decrypt(cipherText);
            System.out.println(plainText);
        }

        // Vigenere Cipher
        {
            VigenereCipher vigenereCipher = new VigenereCipher("TYUHN");
            String plainText = "HELLOTHERE";
            String cipherText = vigenereCipher.encrypt(plainText);
            System.out.println(cipherText);
            plainText = vigenereCipher.decrypt(cipherText);
            System.out.println(plainText);
        }

    }

}
