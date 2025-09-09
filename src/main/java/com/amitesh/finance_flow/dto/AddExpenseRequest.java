package com.amitesh.finance_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseRequest {
    private String userId;
    private Double amount;
    private String category;
    private String description;
    private int year;
    private int month;
    private Date date;
    private String paymentMethod;
    private String tag;
    private String note;
}
