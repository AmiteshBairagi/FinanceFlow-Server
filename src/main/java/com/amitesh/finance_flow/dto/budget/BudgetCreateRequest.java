package com.amitesh.finance_flow.dto.budget;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetCreateRequest {
    private String budgetName;
    private double budgetAmount;
    private String categoryId;
    private int year;
    private int month;
    private boolean isYearly;
    private String startDate;
    private String endDate;
    private String description;


    public boolean getIsYearly() {
        return isYearly;
    }
}
