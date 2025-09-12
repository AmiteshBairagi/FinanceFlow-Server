package com.amitesh.finance_flow.service;


import com.amitesh.finance_flow.dto.BudgetCreateRequest;
import com.amitesh.finance_flow.model.User;
import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.model.budgets.UserBudget;
import com.amitesh.finance_flow.repo.BudgetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BudgetService {
    private final BudgetRepository repo;

    public BudgetService(BudgetRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> createBudget(BudgetCreateRequest req) {

        Budget budget = Budget.builder()
                .budgetId(UUID.randomUUID().toString())
                .budgetName(req.getBudgetName())
                .budgetAmount(req.getBudgetAmount())
                .category(req.getCategory())
                .period(req.getPeriod())
                .startDate(req.getStartDate())
                .description(req.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        try{
            String userId = req.getUserId();
            UserBudget userBudget = repo.findByUserId(userId);
            if(userBudget == null){
                userBudget = UserBudget.builder()
                        .userId(userId)
                        .budgets(new ArrayList<>())
                        .build();
            }

            userBudget.getBudgets().add(budget);
            repo.save(userBudget);
            return ResponseEntity.status(HttpStatus.CREATED).body("Budget Successfully Created");

        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create budget" + e.getMessage());
        }

    }
}
