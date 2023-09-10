package com.project;

import java.util.Scanner;

public class PasswordManager {

    private static final int OFFSET = 3;

    public static String encrypt(String password) {
        if (!isValid(password)) {
            return "密码不合法，请重新输入";
        }
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + i + 1 + OFFSET);
        }
        swap(chars, 0, chars.length - 1);
        reverse(chars);
        return new String(chars);
    }

    public static String decrypt(String password) {
        if (!isValid(password)) {
            return "密码不合法，请重新输入";
        }
        char[] chars = password.toCharArray();
        reverse(chars);
        swap(chars, 0, chars.length - 1);
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - i - 1 - OFFSET);
        }
        return new String(chars);
    }

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

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

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

        System.out.println("请输入一个密码：");
        String password = scanner.nextLine();
        String encryptedPassword = encrypt(password);
        System.out.println("加密后的密码是：" + encryptedPassword);

        System.out.println("请输入一个密码：");
        password = scanner.nextLine();
        String decryptedPassword = decrypt(password);
        System.out.println("解密后的密码是：" + decryptedPassword);

        scanner.close();
    }
}
