package com.ssb.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    /**
     * 生成用户昵称
     * @param length 长度
     * @return u_ea3e7b1cc2584ced
     */
    public static String generateNickName(int length) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        if (length > 0 && length < uuid.length()) {
            return "u_" + uuid.substring(0, length - 2);
        }
        return "u_" + uuid.substring(0, 10);
    }

}
