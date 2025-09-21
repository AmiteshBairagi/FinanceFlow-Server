package com.amitesh.finance_flow.model.dashboard.monthlyStats;


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
@Document(collection = "monthly_dashboard_stats")
public class MonthlyDashboardStats {
    @Id
    private String id;
    private String userId;
    private int year;
    private int month;
    private double totalIncome;
    private double totalExpense;
    private double netSavings;
    private List<CategoryBreakdown> breakdowns;
    private Instant createdAt;
    private Instant updatedAt;
}
