package com.amitesh.finance_flow.service.mail;

import com.amitesh.finance_flow.dto.mail.GetOtpRequest;
import com.amitesh.finance_flow.service.utils.OtpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public ResponseEntity<?> getOtp(GetOtpRequest req) {
        String email = req.getEmail();
        String password = req.getPassword();
        String otp = OtpUtil.generateOtp();

        //TODO -- Send OTP to the mail id

        return ResponseEntity.status(HttpStatus.OK).body(otp);
    }


}
