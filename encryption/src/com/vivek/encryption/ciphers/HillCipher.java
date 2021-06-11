package com.vivek.encryption.ciphers;

import com.vivek.encryption.algorithm.StringCipher;
import com.vivek.encryption.utils.Utils;

public class HillCipher implements StringCipher {

    private final int[][] keyMatrix;
    private final int[][] keyInvertedMatrix;

    public HillCipher(String key) {
        keyMatrix = new int[3][3];
        keyInvertedMatrix = new int[3][3];
        generateKeyMatrix(key);
        Utils.inverseMatrix(keyMatrix, keyInvertedMatrix);
    }

    @Override
    public String encrypt(String plainText) {
        int[][] plainVector = new int[3][1];

        // generate plaintext vector
        for (int i = 0; i < 3; i++)
            plainVector[i][0] = plainText.charAt(i) % 65;

        int[][] cipherVector = new int[3][1];

        matrixMultiplication(plainVector, keyMatrix, cipherVector);

        // generate string from cipherVector
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++)
            builder.append((char) (cipherVector[i][0] + 65));

        return builder.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        int[][] cipherVector = new int[3][1];

        // generate cipherText vector
        for (int i = 0; i < 3; i++)
            cipherVector[i][0] = cipherText.charAt(i) % 65;

        int[][] plainVector = new int[3][1];

        matrixMultiplication(cipherVector, keyInvertedMatrix, plainVector);

        // generate string from cipherVector
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++)
            builder.append((char) (plainVector[i][0] + 65));

        return builder.toString();
    }

    private void generateKeyMatrix(String key) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = key.charAt(k) % 65;
                k++;
            }
        }
    }

    private void matrixMultiplication(int[][] inputVector, int[][] keySquareMatrix, int[][] outputVector) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                outputVector[i][0] += keySquareMatrix[i][j] * inputVector[j][0];
            }
            outputVector[i][0] = outputVector[i][0] % 26;
        }
    }

}
