package com.amitesh.finance_flow.model.monthly_expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private String transactionId;
    private double amount;
    private String category;
    private String description;
    private Date date;
    private String paymentMethod;
    private String note;
    private String tag;
}
