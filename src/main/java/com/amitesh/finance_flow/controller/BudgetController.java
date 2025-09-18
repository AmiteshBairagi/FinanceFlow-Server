package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.BudgetCreateRequest;
import com.amitesh.finance_flow.service.BudgetService;
import com.amitesh.finance_flow.service.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBudget(@RequestBody BudgetCreateRequest req){
        return budgetService.createBudget(req);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBudget(@RequestParam String budgetId){
        return budgetService.deleteBudget(budgetId,getCurrentUser().getUserId());
    }

    @GetMapping("/all-budgets")
    public ResponseEntity<?> getAllBudgets(){

        return budgetService.getAllBudgets(getCurrentUser().getUserId());
    }
}
