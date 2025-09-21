package com.amitesh.finance_flow.model.income;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Income {
    private String incomeId;
    private double incomeAmount;
    private String description;
    private LocalDate date;
    private String note;
    private Instant createdAt;
    private Instant updatedAt;
}
