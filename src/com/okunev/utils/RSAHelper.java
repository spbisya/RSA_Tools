package com.okunev.utils;

import javax.crypto.Cipher;
import java.io.*;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by gwa on 12/22/16.
 */
public class RSAHelper {
    private static RSAHelper helper = null;
    private final String ALGORITHM = "RSA";
    private final String PRIVATE_KEY_FILE = "private.key";
    private final String PUBLIC_KEY_FILE = "public.key";

    private RSAHelper() throws Exception {
        if (!areKeysPresent())
            generateKey();
    }

    public static RSAHelper getInstance() throws Exception {
        if (helper == null) {
            return new RSAHelper();
        }
        return helper;
    }

    //Генерируем ключ, содержащий приватный и публичный ключ, используя 4096 бит. Сохраняем ключи в файлы.
    private void generateKey() {
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(4096);
            final KeyPair key = keyGen.generateKeyPair();

            File privateKeyFile = new File(PRIVATE_KEY_FILE);
            File publicKeyFile = new File(PUBLIC_KEY_FILE);

            // Создаём файлы для ключей
            if (privateKeyFile.getParentFile() != null) {
                privateKeyFile.getParentFile().mkdirs();
            }
            privateKeyFile.createNewFile();

            if (publicKeyFile.getParentFile() != null) {
                publicKeyFile.getParentFile().mkdirs();
            }
            publicKeyFile.createNewFile();

            // Сохраняем публичный ключ
            ObjectOutputStream publicKeyOS = new ObjectOutputStream(
                    new FileOutputStream(publicKeyFile));
            publicKeyOS.writeObject(key.getPublic());
            publicKeyOS.close();

            // Сохраняем приватный ключ
            ObjectOutputStream privateKeyOS = new ObjectOutputStream(
                    new FileOutputStream(privateKeyFile));
            privateKeyOS.writeObject(key.getPrivate());
            privateKeyOS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Проверим, были ли сгенерированы ключи
    private boolean areKeysPresent() {
        File privateKey = new File(PRIVATE_KEY_FILE);
        File publicKey = new File(PUBLIC_KEY_FILE);
        return privateKey.exists() && publicKey.exists();
    }

    // Зашифруем текст публичным ключом
    public byte[] encrypt(String text) throws Exception {
        ObjectInputStream inputStream;

        // Зашифровываем
        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        final PublicKey publicKey = (PublicKey) inputStream.readObject();
        byte[] cipherText = null;
        try {
            // Получаем объект алгоритма
            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Шифруем текст с помощью ключа
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }

    // Расшифровываем текст приватным ключом
    public String decrypt(byte[] text) throws Exception {
        ObjectInputStream inputStream;
        inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
        byte[] dectyptedText = null;
        try {
            // Получаем объект алгоритма
            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            // Расшифровка текста
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            dectyptedText = cipher.doFinal(text);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        assert dectyptedText != null;
        return new String(dectyptedText);
    }

}
