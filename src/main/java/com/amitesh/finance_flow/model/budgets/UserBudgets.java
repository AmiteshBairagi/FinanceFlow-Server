package com.amitesh.finance_flow.model.budgets;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_budgets")
@Builder
public class UserBudgets {
    @Id
    private String id;
    private String userId;
    private int year;
    private int month;
    private boolean isYearly;
    private List<Budget> budgets = new ArrayList<>();
    private Instant createdAt;
}
