package com.amitesh.finance_flow.controller;


import com.amitesh.finance_flow.dto.expense.AddExpenseRequest;
import com.amitesh.finance_flow.service.UserPrincipal;
import com.amitesh.finance_flow.service.expense.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody AddExpenseRequest req){
        return expenseService.addExpense(req,getCurrentUser().getUserId());
    }


    // TODO -- pagination and filtering all with this same API
    @GetMapping("/get-expense")
    public ResponseEntity<?> getExpenseByYearAndMonth(@RequestParam int year, @RequestParam(defaultValue = "0")  int month){
        String userId = getCurrentUser().getUserId();
        if(month == 0){
            return expenseService.getExpenseByYear(year,userId);
        }else{
            return expenseService.getExpenseByYearAndMonth(year,month,getCurrentUser().getUserId());
        }

    }


    @GetMapping("get-all-expense")
    public ResponseEntity<?> getAllExpense(){
        return expenseService.getAllExpense(getCurrentUser().getUserId());
    }


}
