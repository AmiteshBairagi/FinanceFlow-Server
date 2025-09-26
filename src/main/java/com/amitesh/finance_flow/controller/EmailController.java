package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.mail.GetOtpRequest;
import com.amitesh.finance_flow.service.mail.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> getOtp(@RequestBody GetOtpRequest req){
        return service.getOtp(req);
    }

    // TODO - verify-otp
}
