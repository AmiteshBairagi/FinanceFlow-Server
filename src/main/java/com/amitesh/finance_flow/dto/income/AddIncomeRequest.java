package com.amitesh.finance_flow.dto.income;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIncomeRequest {
    private double incomeAmount;
    private String incomeSource;
    private String categoryId;
    private LocalDate date;
    private String paymentMethodId;
    private String note;
    private int year;
    private int month;
}
