package com.amitesh.finance_flow.model.income;

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
@Document(collection = "user_income")
public class UserIncome {
    @Id
    private String id;
    private String userId;
    private int year;
    private int month;
    private List<Income> incomes;
    private Instant createdAt;
}
