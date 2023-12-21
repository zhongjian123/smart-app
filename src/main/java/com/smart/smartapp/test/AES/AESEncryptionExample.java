package com.smart.smartapp.test.AES;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/12/11
 */
public class AESEncryptionExample {
    public static String encryptAES(String plaintext, SecretKey secretKey, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        byte[] cipherText = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
        String str = new String(cipherText, StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(cipherText);
    }

    public static String decryptAES(String cipherText, SecretKey secretKey, IvParameterSpec iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedText, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String key="fakhycga1jm9n2gj";
        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");

        String viStr="jf6bw0l0bqhs64ew";
        IvParameterSpec iv = new IvParameterSpec(viStr.getBytes());

        // 待加密的明文
        String plaintext = "admin";

        // 加密
        String encryptedText = encryptAES(plaintext, secretKey, iv);
        System.out.println("Encrypted Text: " + encryptedText);

        // 解密
        String decryptedText = decryptAES(encryptedText, secretKey, iv);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
