package com.amitesh.finance_flow.dto;

import com.amitesh.finance_flow.model.budgets.Budget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetWrapper {
    private Budget budget;
}
