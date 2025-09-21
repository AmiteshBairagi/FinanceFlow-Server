package com.amitesh.finance_flow.service.paymentMethod;

import com.amitesh.finance_flow.dto.paymentMethod.CreatePaymentMethodRequest;
import com.amitesh.finance_flow.model.paymentMethod.PaymentMethod;
import com.amitesh.finance_flow.model.paymentMethod.UserPaymentMethods;
import com.amitesh.finance_flow.repo.paymentMethod.PaymentMethodRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repo;

    public PaymentMethodService(PaymentMethodRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addPaymentMethod(CreatePaymentMethodRequest req, String userId) {
        try{
            PaymentMethod paymentMethod = PaymentMethod.builder()
                    .paymentMethodId(UUID.randomUUID().toString())
                    .paymentMethodName(req.getPaymentMethodName())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();

            UserPaymentMethods userPaymentMethods = repo.findByUserId(userId);

            if(userPaymentMethods == null){
                userPaymentMethods = new UserPaymentMethods();
                userPaymentMethods.setUserId(userId);
                userPaymentMethods.setPaymentMethods(new ArrayList<>(Arrays.asList(paymentMethod)));
                userPaymentMethods.setCreatedAt(Instant.now());
            }else{
                userPaymentMethods.getPaymentMethods().add(paymentMethod);
            }

            repo.save(userPaymentMethods);
            return ResponseEntity.status(HttpStatus.OK).body("Payment Method added successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
