package com.amitesh.finance_flow.model.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentMethod {
    private String paymentMethodId;
    private String paymentMethodName;
    private Instant createdAt;
    private Instant updatedAt;
}
