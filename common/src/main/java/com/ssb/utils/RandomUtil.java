package com.ssb.utils;

import java.util.Random;

/**
 * 随机生成类
 */
public class RandomUtil {
    private static final String DIGITS="0123456789";
    private static final Random random = new Random();

    /**
     * 随机生成指定长度字符串
     * @param length 指定长度
     * @return 返回指定长度字符串
     */
    public static String generateCode(int length){
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return sb.toString();
    }
}
