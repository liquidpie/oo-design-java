package com.vivek.encryption.algorithm;

import java.io.File;

public interface FileCipher {

    void encryptFile(File inFile, File outFile) throws Exception;

    void decryptFile(File inFile, File outFile) throws Exception;

}
