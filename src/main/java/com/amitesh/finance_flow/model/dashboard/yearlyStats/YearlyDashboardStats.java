package com.amitesh.finance_flow.model.dashboard.yearlyStats;


import com.amitesh.finance_flow.model.dashboard.CategoryBreakdown;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "yearly_dashboard_stats")
public class YearlyDashboardStats {
    @Id
    private String id;
    private String userId;
    private int year;
    private double totalIncome;
    private double totalExpense;
    private double netSavings;
    private List<CategoryBreakdown> breakdowns;
    private Instant createdAt;
    private Instant updatedAt;
}
