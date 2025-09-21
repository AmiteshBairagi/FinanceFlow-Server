package com.amitesh.finance_flow.dto.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseRequest {
    private Double amountSpent;
    private String categoryId;
    private String description;
    private LocalDate date;
    private String paymentMethodId;
    private String note;
    private int year;
    private int month;
}
