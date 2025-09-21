package com.amitesh.finance_flow.model.expense;


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
@Document(collection = "user_expense")
public class UserExpense {
    @Id
    private String id;
    private String userId;
    private int year;
    private int month;
    private List<Expense> expenses;
    private Instant createdAt;
}
