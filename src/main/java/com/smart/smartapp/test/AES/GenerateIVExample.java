package com.smart.smartapp.test.AES;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/12/11
 */
public class GenerateIVExample {
    public static String generateRandomIV() {
        // 创建一个安全的随机数生成器
        SecureRandom secureRandom = new SecureRandom();

        // 生成16字节的随机IV
        byte[] ivBytes = new byte[16];
        secureRandom.nextBytes(ivBytes);

        // 将随机生成的IV转换为Base64编码的字符串
        return Base64.getEncoder().encodeToString(ivBytes);
    }

    public static void main(String[] args) {
        // 生成随机的IV字符串
        String randomIV = generateRandomIV();
        System.out.println("Random IV: " + randomIV);

        // 在实际应用中，将生成的IV字符串传递给加密和解密的方法
        // IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(randomIV));
    }
}
