package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.budget.BudgetCreateRequest;
import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.service.budget.BudgetService;
import com.amitesh.finance_flow.service.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return budgetService.createBudget(req,getCurrentUser().getUserId());
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<?> deleteBudget(@RequestParam String budgetId){
//        return budgetService.deleteBudget(budgetId,getCurrentUser().getUserId());
//    }
//
//    @GetMapping("/all-budgets")
//    public ResponseEntity<?> getAllBudgets(){
//
//        return budgetService.getAllBudgets(getCurrentUser().getUserId());
//    }
//
//    @GetMapping("/{userId}/{budgetId}")
//    public ResponseEntity<?> getBudget(@PathVariable String userId, @PathVariable String budgetId){
//        Budget budget = budgetService.getBudget(userId,budgetId);
//        return ResponseEntity.ok(budget);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<?> updateBudget(@RequestParam String budgetId, @RequestBody BudgetCreateRequest req){
//        return budgetService.updateBudget(budgetId,req);
//    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBudget(@RequestParam String keyword){
        List<Budget> matchedBudgets =  budgetService.searchBudget(getCurrentUser().getUserId(), keyword);
        return ResponseEntity.ok(matchedBudgets);
    }
}
