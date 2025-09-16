package com.amitesh.finance_flow.service;

import com.amitesh.finance_flow.dto.AddExpenseRequest;
import com.amitesh.finance_flow.model.monthly_expense.Expense;
import com.amitesh.finance_flow.model.monthly_expense.MonthlyExpense;
import com.amitesh.finance_flow.rabbitMQEvent.transactionEvent.TransactionEventPublisher;
import com.amitesh.finance_flow.repo.MonthlyExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class MonthlyExpenseService {
    private final MonthlyExpenseRepository repo;
    private final TransactionEventPublisher eventPublisher;

    public MonthlyExpenseService(MonthlyExpenseRepository repo, TransactionEventPublisher eventPublisher) {
        this.repo = repo;
        this.eventPublisher = eventPublisher;
    }

    public ResponseEntity<?> addExpense(AddExpenseRequest req) {
        MonthlyExpense document = repo.findByUserIdAndYearAndMonth(req.getUserId(),req.getYear(),req.getMonth());

        if(document == null){
            document = MonthlyExpense.builder()
                    .userId(req.getUserId())
                    .year(req.getYear())
                    .month(req.getMonth())
                    .expenses(new ArrayList<>())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
        }

        String transactionId = UUID.randomUUID().toString();
        document.getExpenses().add(new Expense(transactionId,req.getAmount(),req.getCategory(),req.getDescription(),req.getDate(),req.getPaymentMethod(),req.getTag(),req.getNote()));
        document.setUpdatedAt(LocalDateTime.now());

        try{
            repo.save(document);
            eventPublisher.publishTransactionCreatedEvent(transactionId,req.getUserId(),req.getAmount());
            return ResponseEntity.status(HttpStatus.OK).body("Expense Added Successfully and Transaction Event Published Successfully");
        }catch(Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save the transaction" + e.getMessage());
        }


    }
}
