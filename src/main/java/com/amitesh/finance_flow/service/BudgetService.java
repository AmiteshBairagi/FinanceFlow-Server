package com.amitesh.finance_flow.service;


import com.amitesh.finance_flow.customException.ResourceNotFoundException;
import com.amitesh.finance_flow.dto.BudgetCreateRequest;
import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.model.budgets.UserBudget;
import com.amitesh.finance_flow.repo.BudgetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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


        try {
            String userId = req.getUserId();
            UserBudget userBudget = repo.findByUserId(userId);
            if (userBudget == null) {
                userBudget = UserBudget.builder()
                        .userId(userId)
                        .budgets(new ArrayList<>())
                        .build();
            }

            userBudget.getBudgets().add(budget);
            repo.save(userBudget);
            return ResponseEntity.status(HttpStatus.CREATED).body("Budget Successfully Created");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create budget" + e.getMessage());
        }

    }

    public ResponseEntity<?> deleteBudget(String budgetId, String userId) {
        try {
            UserBudget userBudget = repo.findByUserId(userId);
            if (userBudget == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<Budget> budgets = userBudget.getBudgets();

            Budget budgetToDelete = null;

            for (Budget budget : budgets) {
                if (budgetId.equals(budget.getBudgetId())) {
                    budgetToDelete = budget;
                    break;
                }
            }

            if (budgetToDelete == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Budget not found");
            }
            budgets.remove(budgetToDelete);

            repo.save(userBudget);

            return ResponseEntity.status(HttpStatus.OK).body("Budget deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete Budget" + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllBudgets(String userId) {
        try {
            UserBudget userBudget = repo.findByUserId(userId);

            if (userBudget == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<Budget> budgets = userBudget.getBudgets();
            return ResponseEntity.status(HttpStatus.OK).body(budgets + "fetched all the budgets");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all the goals" + e.getMessage());
        }
    }

    public Budget getBudget(String userId, String budgetId) {
        UserBudget userBudget = repo.findByUserId(userId);
        if (userBudget == null) {
            throw new ResourceNotFoundException("Budget Not Found");

        }

        return userBudget.getBudgets().stream()
                .filter(budget -> budget.getBudgetId().equals(budgetId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Budget Not Found With the BudgetID " + budgetId));


    }
}