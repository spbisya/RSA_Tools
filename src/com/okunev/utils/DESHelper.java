package com.okunev.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

/**
 * Created by gwa on 12/22/16.
 */
public class DESHelper extends AlgorithmHelper {

    private static DESHelper helper = null;
    private final String ALGORITHM = "DESede";
    private Key desKey = null;
    private Cipher cipher = null;

    private DESHelper() throws Exception {
        initCipher();
    }

    public static DESHelper getInstance() throws Exception {
        if (helper == null) {
            return new DESHelper();
        }
        return helper;
    }

    @Override
    public byte[] encrypt(String input) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, desKey);
        byte[] inputBytes = input.getBytes();
        return cipher.doFinal(inputBytes);
    }

    @Override
    public String decrypt(byte[] encryptionBytes) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, desKey);
        byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
        String recovered = new String(recoveredBytes);
        return recovered;
    }

    @Override
    protected void initCipher() throws Exception {
        desKey = KeyGenerator.getInstance(ALGORITHM).generateKey();
        cipher = Cipher.getInstance(ALGORITHM);
    }
}
