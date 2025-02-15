package com.heavydelay.BandsSync.Api.util;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    public static String generateVerificationToken(){
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

}
