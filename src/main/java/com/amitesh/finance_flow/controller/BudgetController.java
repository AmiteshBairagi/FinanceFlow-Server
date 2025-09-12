package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.BudgetCreateRequest;
import com.amitesh.finance_flow.service.BudgetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
