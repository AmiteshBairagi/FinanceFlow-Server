package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.AddExpenseRequest;
import com.amitesh.finance_flow.model.monthly_expense.MonthlyExpense;
import com.amitesh.finance_flow.service.MonthlyExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expense")
public class MonthlyExpenseController {

    private final MonthlyExpenseService monthlyExpenseService;

    public MonthlyExpenseController(MonthlyExpenseService monthlyExpenseService) {
        this.monthlyExpenseService = monthlyExpenseService;
    }

    @PostMapping("/add")
    public ResponseEntity<?>  addExpense(@RequestBody AddExpenseRequest req){
        return monthlyExpenseService.addExpense(req);
    }
}
