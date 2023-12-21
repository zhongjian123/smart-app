package com.smart.smartapp.test.AES;

import java.util.Base64;

/**
 * @description:
 * @author: zhizj
 * @date: 2023/12/11
 */
public class Base64EncodeExample {
    public static String base64Encode(String plainText) {
        // 使用Base64编码
        byte[] encodedBytes = Base64.getEncoder().encode(plainText.getBytes());

        // 将字节数组转换为Base64编码的字符串
        return new String(encodedBytes);
    }

    public static String base64Decode(String encodedString) {
        // 使用Base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

        // 将字节数组转换为字符串
        return new String(decodedBytes);
    }

    public static void main(String[] args) {
        // 假设有一个字符串
        String plainText = "admin";

        // 使用Base64编码字符串
        String base64EncodedString = base64Encode(plainText);

        System.out.println("Base64 Encoded Result: " + base64EncodedString);

        String s = base64Decode(base64EncodedString);
        System.out.println(s);
    }
}
