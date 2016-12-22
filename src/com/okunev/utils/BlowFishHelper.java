package com.okunev.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;

/**
 * Created by gwa on 12/22/16.
 */
public class BlowFishHelper extends AlgorithmHelper {
    private static BlowFishHelper helper = null;
    private final String ALGORYTHM_SHORT = "Blowfish";
    private Cipher cipher = null;
    private Key blowFishKey = null;

    private BlowFishHelper() throws Exception {
        initCipher();
    }

    public static BlowFishHelper getInstance() throws Exception {
        if (helper == null) {
            return new BlowFishHelper();
        }
        return helper;
    }

    @Override
    public byte[] encrypt(String input) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, blowFishKey);
        byte[] encryptedData = cipher.doFinal(input.getBytes());
        return encryptedData;
    }

    @Override
    public String decrypt(byte[] encryptionBytes) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, blowFishKey);
        byte[] data = cipher.doFinal(encryptionBytes);
        String recovered = new String(data);
        return recovered;
    }

    @Override
    protected void initCipher() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORYTHM_SHORT);
        keyGenerator.init(128);
        blowFishKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
    }
}
