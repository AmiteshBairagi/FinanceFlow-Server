package com.amitesh.finance_flow.model.budgets;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_budgets")
@Builder
public class UserBudget {
    @Id
    private String id;
    private String userId;
    private List<Budget> budgets = new ArrayList<>();
}
