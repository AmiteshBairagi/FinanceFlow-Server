package com.amitesh.finance_flow.service.utils;

import java.security.SecureRandom;

public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();
    private static final int OTP_LENGTH = 6;

    public static String generateOtp(){
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            // Generate a digit between 1 and 9
            int digit = random.nextInt(9) + 1;  // 1–9
            otp.append(digit);
        }

        return otp.toString();
    }
}
