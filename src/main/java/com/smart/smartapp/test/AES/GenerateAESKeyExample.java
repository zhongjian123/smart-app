package com.smart.smartapp.test.AES;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/12/11
 */
public class GenerateAESKeyExample {
    public static String generateAESKeyAsString() throws NoSuchAlgorithmException {
        // 使用AES算法生成密钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        // 设置密钥长度为256位
        keyGenerator.init(128);

        // 生成密钥
        SecretKey aesKey = keyGenerator.generateKey();

        String dd = new String(aesKey.getEncoded(), StandardCharsets.UTF_8);
        // 将密钥的字节数组表示转换为Base64编码的字符串
        return Base64.getEncoder().encodeToString(aesKey.getEncoded());
    }

    public static void main(String[] args) {
        try {
            // 生成AES密钥并输出为字符串
            for (int i = 0; i <= 500000; i++) {
                String aesKeyAsString = generateAESKeyAsString();
                if (aesKeyAsString.matches("[a-zA-Z0-9]+")) {
                    // 在实际应用中，你可以将aesKeyAsString存储在安全的地方，以供加密和解密时使用
                    System.out.println("AES Key as String: " + aesKeyAsString);
                    break;
                }

            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
