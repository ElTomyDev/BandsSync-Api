package com.heavydelay.BandsSync.Api.util;

import java.security.SecureRandom;

public class AccessCodeGenerator {
    private static final String MAYUS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMS = "0123456789";
    private static final String CHARACTERS = MAYUS + MINUS + NUMS;
    private static final SecureRandom random = new SecureRandom();

    public static String generateAccessCode(int length){
        if (length < 1 || length > 6){
            throw new IllegalArgumentException("The code length must be greater than 1 and less than 6");
        }

        byte[] bytes = new byte[length];
        random.nextBytes(bytes);

        StringBuilder code = new StringBuilder(length);
        for (byte b : bytes) {
            int index = (b & 0xFF) % CHARACTERS.length();
            code.append(CHARACTERS.charAt(index));
        }
        return code.toString();
    }
}
