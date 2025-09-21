package com.amitesh.finance_flow.dto.paymentMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentMethodRequest {
    private String paymentMethodName;

}
