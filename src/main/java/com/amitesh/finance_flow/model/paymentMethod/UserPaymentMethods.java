package com.amitesh.finance_flow.model.paymentMethod;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_payment_methods")
public class UserPaymentMethods {
    @Id
    private String id;
    private String userId;
    private List<PaymentMethod> paymentMethods;
    private Instant createdAt;
}
