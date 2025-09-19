package com.amitesh.finance_flow.dto.budget;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetCreateRequest {
    private String userId;
    private String budgetName;
    private String category;
    private double budgetAmount;
    private String period;
    private LocalDate startDate;
    private String description;

}
