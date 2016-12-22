package com.okunev.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by gwa on 12/22/16.
 */
public class AESHelper extends AlgorithmHelper {
    private static AESHelper helper = null;
    private final String ALGORITHM = "AES";
    private Cipher cipher = null;
    private SecretKeySpec aesKey = null;

    private AESHelper() throws Exception {
        initCipher();
    }

    public static AESHelper getInstance() throws Exception {
        if (helper == null) {
            return new AESHelper();
        }
        return helper;
    }

    @Override
    public byte[] encrypt(String input) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedData = cipher.doFinal(input.getBytes());
        return encryptedData;
    }

    @Override
    public String decrypt(byte[] encryptionBytes) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] data = cipher.doFinal(encryptionBytes);
        String recovered = new String(data);
        return recovered;
    }

    @Override
    protected void initCipher() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[16];
        random.nextBytes(bytes);
        cipher = Cipher.getInstance(ALGORITHM);
        aesKey = new SecretKeySpec(bytes, ALGORITHM);
    }
}
