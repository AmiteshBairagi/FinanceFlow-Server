package com.amitesh.finance_flow.service.income;

import com.amitesh.finance_flow.dto.income.AddIncomeRequest;
import com.amitesh.finance_flow.model.income.Income;
import com.amitesh.finance_flow.model.income.UserIncome;
import com.amitesh.finance_flow.repo.income.IncomeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

@Service
public class IncomeService {

    private final IncomeRepository repo;

    public IncomeService(IncomeRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addIncome(AddIncomeRequest req, String userId) {

        //TODO - before saving must check if the categoryId , paymentMethodId actually valid or not for the user;
        Income income = Income.builder()
                .incomeId(UUID.randomUUID().toString())
                .incomeAmount(req.getIncomeAmount())
                .categoryId(req.getCategoryId())
                .incomeSourcce(req.getIncomeSource())
                .date(req.getDate())
                .paymentMethodId(req.getPaymentMethodId())
                .note(req.getNote())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();


        try{
            UserIncome userIncome = repo.findByUserIdAndYearAndMonth(userId,req.getYear(),req.getMonth());
            if(userIncome == null){
                UserIncome newUserIncome = UserIncome.builder()
                        .userId(userId)
                        .year(req.getYear())
                        .month(req.getMonth())
                        .incomes(Arrays.asList(income))
                        .createdAt(Instant.now())
                        .build();

                repo.save(newUserIncome);
                return ResponseEntity.status(HttpStatus.OK).body("Income saved successfully");
            }else{
                userIncome.getIncomes().add(income);
                repo.save(userIncome);
                return ResponseEntity.status(HttpStatus.OK).body("Income saved successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
