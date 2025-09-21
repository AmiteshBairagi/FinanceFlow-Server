package com.amitesh.finance_flow.model.dashboard;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBreakdown {
    private String categoryBreakdownId;
    private String categoryId;
    private double amountSpent;
    private Instant createdAt;
    private Instant updatedAt;
}
