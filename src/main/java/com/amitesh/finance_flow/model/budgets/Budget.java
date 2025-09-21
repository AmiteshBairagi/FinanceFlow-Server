package com.amitesh.finance_flow.model.budgets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.Instant;
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
    private double usedAmount;
    private String categoryId;
    private String startDate;
    private String endDate;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;
}
