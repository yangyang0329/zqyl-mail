package com.ewomail.interfaces.dto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryption {

    /**
     * 生成输入字符串的MD5哈希值
     *
     * @param input 需要加密的字符串
     * @return MD5哈希值的十六进制表示
     */
    public static String getMD5(String input) {
        try {
            // 创建MD5消息摘要实例
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 将输入字符串转换为字节数组并进行哈希计算
            byte[] messageDigest = md.digest(input.getBytes());

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                // 将每个字节转换为两位的十六进制数
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 如果只有一位，前面补0
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // MD5算法应该始终存在，因此此异常不太可能发生
            throw new RuntimeException(e);
        }
    }

    public static void main1(String[] args) {
        // 已知输入和预期输出
        String input1 = "test123456";

        String input2 = "test1234";

        // 生成实际的MD5哈希值
        String actualHash1 = getMD5(input1);
        String actualHash2 = getMD5(input2);

        // 输出结果并验证
        System.out.println("输入: " + input1);
        System.out.println("实际生成的MD5哈希值: " + actualHash1);
        System.out.println();

        System.out.println("输入: " + input2);
        System.out.println("实际生成的MD5哈希值: " + actualHash2);
    }
    public static void main(String[] args) {
        String targetHash = "16d7a4fca7442dda3ad93c9a726597e4"; // 目标MD5哈希值
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        int maxLength = 8; // 设置最大尝试长度

        // 开始暴力破解
        for (int length = 1; length <= maxLength; length++) {
            char[] attempt = new char[length];
            if (attemptRecursive(attempt, 0, characters, targetHash)) {
                break;
            }
        }
    }

    /**
     * 递归尝试所有可能的字符组合
     */
    private static boolean attemptRecursive(char[] attempt, int position, String characters, String targetHash) {
        if (position == attempt.length) {
            String attemptStr = new String(attempt);
            String hash = getMD5(attemptStr);
            if (hash.equals(targetHash)) {
                System.out.println("找到匹配的字符串: " + attemptStr);
                return true;
            }
            return false;
        }

        for (char c : characters.toCharArray()) {
            attempt[position] = c;
            if (attemptRecursive(attempt, position + 1, characters, targetHash)) {
                return true;
            }
        }

        return false;
    }
}
