package com.amitesh.finance_flow.service.expense;

import com.amitesh.finance_flow.dto.expense.AddExpenseRequest;
import com.amitesh.finance_flow.model.expense.Expense;
import com.amitesh.finance_flow.model.expense.UserExpense;
import com.amitesh.finance_flow.repo.expense.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository repo;

    public ExpenseService(ExpenseRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addExpense(AddExpenseRequest req, String userId) {
        try{
            Expense expense = Expense.builder()
                    .expenseId(UUID.randomUUID().toString())
                    .amountSpent(req.getAmountSpent())
                    .categoryId(req.getCategoryId())
                    .description(req.getDescription())
                    .date(req.getDate())
                    .paymentMethodId(req.getPaymentMethodId())
                    .note(req.getNote())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();

            UserExpense userExpense = repo.findByUserId(userId);
            if(userExpense == null){
                userExpense = new UserExpense();
                userExpense.setUserId(userId);
                userExpense.setYear(req.getYear());
                userExpense.setMonth(req.getMonth());
                userExpense.setExpenses(new ArrayList<>(Arrays.asList(expense)));
                userExpense.setCreatedAt(Instant.now());
            }else{
                userExpense.getExpenses().add(expense);
            }

            repo.save(userExpense);
            return ResponseEntity.status(HttpStatus.OK).body("Expense Successfully Saved In The Database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
