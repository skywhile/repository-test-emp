package com.project;

import java.util.Scanner;

public class PasswordManager2 {

    // 偏移值
    private static final int OFFSET = 3;

    // 加密功能
    public static String encrypt(String password) {
        // 检查密码是否合法
        if (!isValid(password)) {
            return "密码不合法，请重新输入";
        }
        // 将字符串转换为字符数组
        char[] chars = password.toCharArray();
        // 遍历字符数组，对每个字符进行加密
        for (int i = 0; i < chars.length; i++) {
            // 将每个字符的ASCII码加上它在字符串中的位置(1开始)和偏移值3
            chars[i] = (char) (chars[i] + i + 1 + OFFSET);
        }
        // 将字符串的第一位和最后一位调换顺序
        swap(chars, 0, chars.length - 1);
        // 将字符串反转
        reverse(chars);
        // 返回加密后的字符串
        return new String(chars);
    }

    // 解密功能
    public static String decrypt(String password) {
        // 检查密码是否合法
        if (!isValid(password)) {
            return "密码不合法，请重新输入";
        }
        // 将字符串转换为字符数组
        char[] chars = password.toCharArray();
        // 将字符串反转
        reverse(chars);
        // 将字符串的第一位和最后一位调换顺序
        swap(chars, 0, chars.length - 1);
        // 遍历字符数组，对每个字符进行解密
        for (int i = 0; i < chars.length; i++) {
            // 将每个字符的ASCII码减去它在字符串中的位置(1开始)和偏移值3
            chars[i] = (char) (chars[i] - i - 1 - OFFSET);
        }
        // 返回解密后的字符串
        return new String(chars);
    }

    // 判断密码是否合法，即每个字符为数字或者大小写字母，长度不超过16
    public static boolean isValid(String password) {
        if (password == null || password.length() == 0 || password.length() > 16) {
            return false;
        }
        for (char c : password.toCharArray()) {
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    // 交换字符数组中两个位置的元素
    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    // 反转字符数组
    public static void reverse(char[] chars) {
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            swap(chars, left, right);
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入密码并加密
        System.out.println("请输入一个密码：");
        String password = scanner.nextLine();
        String encryptedPassword = encrypt(password);
        System.out.println("加密后的密码是：" + encryptedPassword);

        // 输入密码并解密
        System.out.println("请输入一个密码：");
        password = scanner.nextLine();
        String decryptedPassword = decrypt(password);
        System.out.println("解密后的密码是：" + decryptedPassword);

        scanner.close();
    }
}
