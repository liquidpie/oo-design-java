package com.vivek.encryption.ciphers;

import com.vivek.encryption.algorithm.FileCipher;
import com.vivek.encryption.algorithm.StringCipher;

import java.io.*;

public class XorCipher implements StringCipher, FileCipher {

    private final byte[] key;

    public XorCipher(byte[] key) {
        this.key = key;
    }

    public XorCipher(String key) {
        assert key != null;
        this.key = key.getBytes();
    }

    /**
     * XOR algorithm encryption / decryption
     *
     * @param data Data (ciphertext / clear text)
     * @return Return decrypted / encrypted data
     */
    @Override
    public byte[] encrypt(byte[] data) {
        if (data == null || data.length == 0)
            return data;

        byte[] result = new byte[data.length];

        // use key byte array to cycle encryption or decryption
        for (int i = 0; i < data.length; i++) {
            // Data is XOR with key, and then XOR with low 8 bits of cyclic variable (increasing complexity)
            result[i] = (byte) (data[i] ^ key[i % key.length] ^ (i & 0xFF));
        }

        return result;
    }

    @Override
    public byte[] decrypt(byte[] data) {
        return encrypt(data);
    }

    @Override
    public String encrypt(String plainText) {
        return new String(encrypt(plainText.getBytes()));
    }

    @Override
    public String decrypt(String cipherText) {
        return new String(decrypt(cipherText.getBytes()));
    }

    /**
     * Encrypt / decrypt file XOR algorithm
     *
     * @param inFile Input file (ciphertext / clear text)
     * @param outFile Result output file
     */
    @Override
    public void encryptFile(File inFile, File outFile) throws Exception {
        // The result output stream, exclusive or operation, byte is a read and write, where cache stream must be used,
        // Wait until the cache reaches a certain number of bytes (10240 bytes) and then write to the disk
        // (otherwise, write to the disk too many times and the speed will be very slow)

        try (InputStream in = new FileInputStream(inFile);
             OutputStream out = new BufferedOutputStream(new FileOutputStream(outFile), 10240)) {

            int b = -1;
            long i = 0;

            // Read one byte of the file in each cycle, and use the key byte array to cycle encryption or decryption
            while ((b = in.read()) != -1) {
                // Data is XOR with key, and then XOR with low 8 bits of cyclic variable (increasing complexity)
                b = (b ^ key[(int) (i % key.length)] ^ (int) (i & 0xFF));
                out.write(b);
                i++;
            }

            out.flush();
        }
    }

    @Override
    public void decryptFile(File inFile, File outFile) throws Exception {
        encryptFile(inFile, outFile);
    }
}
