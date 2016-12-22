package com.okunev.utils;

/**
 * Created by gwa on 12/22/16.
 */
public abstract class AlgorithmHelper {

    public abstract byte[] encrypt(String input) throws Exception;

    public abstract String decrypt(byte[] encryptionBytes) throws Exception;

    protected abstract void initCipher() throws Exception;

}
