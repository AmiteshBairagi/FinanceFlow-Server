package com.amitesh.finance_flow.model.monthly_expense;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "monthly_expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyExpense {
    @Id
    private String id;
    private String userId;
    private int year;
    private int month;
    private List<Expense> expenses = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
