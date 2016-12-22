package com.okunev;

import com.okunev.utils.AESHelper;
import com.okunev.utils.AlgorithmHelper;
import com.okunev.utils.BlowFishHelper;
import com.okunev.utils.DESHelper;

import java.util.Arrays;

/**
 * Created by gwa on 12/22/16.
 */
public class SymmetricEncryption {

    public static void testAlgorithms(String input)
            throws Exception {
        AlgorithmHelper helper = null;
        byte[] encryptionBytes = null;

        helper = DESHelper.getInstance();
        System.out.println("Triple DES algorithm [1/3]");
        System.out.println("https://en.wikipedia.org/wiki/Triple_DES");
        System.out.println("Entered: " + input);
        encryptionBytes = helper.encrypt(input);
        System.out.println("Encrypted: " + Arrays.toString(encryptionBytes));
        System.out.println("Recovered: " + helper.decrypt(encryptionBytes));

        helper = AESHelper.getInstance();
        System.out.println("\nAES 128 algorithm [2/3]");
        System.out.println("https://en.wikipedia.org/wiki/Advanced_Encryption_Standard");
        System.out.println("Entered: " + input);
        encryptionBytes = helper.encrypt(input);
        System.out.println("Encrypted: " + Arrays.toString(encryptionBytes));
        System.out.println("Recovered: " + helper.decrypt(encryptionBytes));

        helper = BlowFishHelper.getInstance();
        System.out.println("\nBlowFish algorithm [3/3]");
        System.out.println("https://www.schneier.com/academic/blowfish/");
        System.out.println("Entered: " + input);
        encryptionBytes = helper.encrypt(input);
        System.out.println("Encrypted: " + Arrays.toString(encryptionBytes));
        System.out.println("Recovered: " + helper.decrypt(encryptionBytes));
    }
}
