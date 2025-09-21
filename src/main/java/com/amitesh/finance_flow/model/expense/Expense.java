package com.amitesh.finance_flow.model.expense;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private String expenseId;
    private double amountSpent;
    private String categoryId;
    private String description;
    private LocalDate date;
    private String paymentMethodId;
    private String note;
    private Instant createdAt;
    private Instant updatedAt;
}
