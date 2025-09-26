package com.amitesh.finance_flow.service.expense;

import com.amitesh.finance_flow.customException.ResourceNotFoundException;
import com.amitesh.finance_flow.dto.expense.AddExpenseRequest;
import com.amitesh.finance_flow.model.expense.Expense;
import com.amitesh.finance_flow.model.expense.UserExpense;
import com.amitesh.finance_flow.rabbitMQEvent.transactionEvent.ExpenseAddedEventPublisher;
import com.amitesh.finance_flow.repo.expense.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository repo;
    private final ExpenseAddedEventPublisher publisher;

    public ExpenseService(ExpenseRepository repo, ExpenseAddedEventPublisher publisher) {
        this.repo = repo;
        this.publisher = publisher;
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

            UserExpense userExpense = repo.findByUserIdAndYearAndMonth(userId,req.getYear(),req.getMonth());
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
            publisher.publishExpenseAddedEvent(userId, req.getCategoryId(), req.getAmountSpent());
            return ResponseEntity.status(HttpStatus.OK).body("Expense Successfully Saved In The Database");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getExpenseByYearAndMonth(int year, int month, String userId) {
        try{
            UserExpense userExpense = repo.findByUserIdAndYearAndMonth(userId,year,month);
            if(userExpense == null){
                throw new ResourceNotFoundException("No expense found for year: " + year + " and month: " + month);
            }else {
                return ResponseEntity.status(HttpStatus.OK).body(userExpense);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getExpenseByYear(int year, String userId) {
        try{
            List<UserExpense> allUserExpense = repo.findByUserIdAndYear(userId,year);
            if(allUserExpense == null){
                throw new ResourceNotFoundException("No expense found in year: " + year);
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(allUserExpense);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getAllExpense(String userId) {
        try{
            List<UserExpense> allExpenses = repo.findByUserId(userId);

            if(allExpenses == null){
                throw  new ResourceNotFoundException("No expense found for the user");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(allExpenses);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
