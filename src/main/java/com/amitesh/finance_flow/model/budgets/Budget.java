package com.amitesh.finance_flow.model.budgets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
    private String budgetId;
    private String budgetName;
    private double budgetAmount;
    private String category;
    private String period;
    private LocalDate startDate;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
