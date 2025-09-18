package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.BudgetCreateRequest;
import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBudget(@RequestBody BudgetCreateRequest req){
        return budgetService.createBudget(req);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBudget(@RequestParam String budgetId, @RequestParam String userId){
        return budgetService.deleteBudget(budgetId,userId);
    }

    @GetMapping("/all-budgets")
    public ResponseEntity<?> getAllBudgets(@RequestParam String userId){
        return budgetService.getAllBudgets(userId);
    }

    @GetMapping("/{userId}/{budgetId}")
    public ResponseEntity<?> getBudget(@PathVariable String userId, @PathVariable String budgetId){
        Budget budget = budgetService.getBudget(userId,budgetId);
        return ResponseEntity.ok(budget);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateBudget(@RequestParam String budgetId, @RequestBody BudgetCreateRequest req){
        return budgetService.updateBudget(budgetId,req);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBudget(@RequestParam String userId, @RequestParam String keyword){
        List<Budget> matchedBudgets =  budgetService.searchBudget(userId,keyword);
        return ResponseEntity.ok(matchedBudgets);
    }
}
