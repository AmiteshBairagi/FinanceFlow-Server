package com.amitesh.finance_flow.service;

import com.amitesh.finance_flow.dto.AddExpenseRequest;
import com.amitesh.finance_flow.model.monthly_expense.Expense;
import com.amitesh.finance_flow.model.monthly_expense.MonthlyExpense;
import com.amitesh.finance_flow.repo.MonthlyExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Service
public class MonthlyExpenseService {
    private final MonthlyExpenseRepository repo;

    public MonthlyExpenseService(MonthlyExpenseRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addExpense(AddExpenseRequest req) {
        MonthlyExpense document = repo.findByUserIdAndYearAndMonth(req.getUserId(),req.getYear(),req.getMonth());

        if(document == null){
            document = MonthlyExpense.builder()
                    .userId(req.getUserId())
                    .year(req.getYear())
                    .month(req.getMonth())
                    .expenses(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

        document.getExpenses().add(new Expense(req.getAmount(),req.getCategory(),req.getDescription(),req.getDate(),req.getPaymentMethod(),req.getTag(),req.getNote()));
        document.setUpdatedAt(LocalDateTime.now());

        try{
            repo.save(document);
            return ResponseEntity.status(HttpStatus.OK).body("Expense Added Successfully");
        }catch(Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save the transaction" + e.getMessage());
        }


    }
}
