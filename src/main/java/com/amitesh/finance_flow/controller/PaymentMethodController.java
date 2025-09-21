package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.paymentMethod.CreatePaymentMethodRequest;
import com.amitesh.finance_flow.service.UserPrincipal;
import com.amitesh.finance_flow.service.paymentMethod.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {
    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPaymentMethod(@RequestBody CreatePaymentMethodRequest req){
        return service.addPaymentMethod(req,getCurrentUser().getUserId());
    }
}
