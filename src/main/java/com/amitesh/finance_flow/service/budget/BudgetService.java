package com.amitesh.finance_flow.service.budget;


import com.amitesh.finance_flow.customException.ResourceNotFoundException;
import com.amitesh.finance_flow.customException.user.UserNotFoundException;
import com.amitesh.finance_flow.dto.budget.BudgetCreateRequest;
import com.amitesh.finance_flow.dto.budget.BudgetWrapper;

import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.model.budgets.UserBudgets;
import com.amitesh.finance_flow.repo.budgets.BudgetRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BudgetService {
    private final BudgetRepository repo;
    private final MongoTemplate mongoTemplate;

    public BudgetService(BudgetRepository repo, MongoTemplate mongoTemplate) {
        this.repo = repo;
        this.mongoTemplate = mongoTemplate;
    }

    public ResponseEntity<?> createBudget(BudgetCreateRequest req, String userId) {
        try{
            Budget budget = Budget.builder()
                    .budgetId(UUID.randomUUID().toString())
                    .budgetName(req.getBudgetName())
                    .budgetAmount(req.getBudgetAmount())
                    .usedAmount(0)
                    .categoryId(req.getCategoryId())
                    .startDate(req.getStartDate())
                    .endDate(req.getEndDate())
                    .description(req.getDescription())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();

            UserBudgets userBudgets = repo.findByUserId(userId);

            if(userBudgets == null){
                userBudgets = UserBudgets.builder()
                        .userId(userId)
                        .year(req.getYear())
                        .month(req.getMonth())
                        .isYearly(req.getIsYearly())
                        .budgets(new ArrayList<>(Arrays.asList(budget)))
                        .createdAt(Instant.now())
                        .build();
            }else{
                userBudgets.getBudgets().add(budget);
            }

            repo.save(userBudgets);

            return ResponseEntity.status(HttpStatus.OK).body("Budget Created Successfully");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public List<Budget> searchBudget(String userId, String keyword) {

        MatchOperation matchUser = Aggregation.match(Criteria.where("userId").is(userId));
        UnwindOperation unwindBudgets = Aggregation.unwind("budgets");

        MatchOperation matchKeyword = Aggregation.match(new Criteria().orOperator(
                Criteria.where("budgets.budgetName").regex(keyword,"i"),
                Criteria.where("budgets.description").regex(keyword,"i")
        ));

        ProjectionOperation projectBudget = Aggregation.project()
                .and("budgets").as("budget");

        Aggregation aggregation = Aggregation.newAggregation(matchUser,unwindBudgets,matchKeyword,projectBudget);
        AggregationResults<BudgetWrapper> results = mongoTemplate.aggregate(aggregation, "user_budgets", BudgetWrapper.class);

        return results.getMappedResults().stream()
                .map(BudgetWrapper::getBudget)
                .collect(Collectors.toList());
    }


}