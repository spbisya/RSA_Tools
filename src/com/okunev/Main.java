package com.okunev;

import com.okunev.utils.RSAHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            final String originalText = reader.readLine();
            RSAHelper helper = RSAHelper.getInstance();
            final byte[] cipherText = helper.encrypt(originalText);
            final String plainText = helper.decrypt(cipherText);
            // Удобный лог
            System.out.println("RSA 4096 algorithm");
            System.out.println("Entered: " + originalText);
            System.out.println("Encrypted: " + Arrays.toString(cipherText));
            System.out.println("Decrypted: " + plainText);
            System.out.println("\n");
            SymmetricEncryption.testAlgorithms(originalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
