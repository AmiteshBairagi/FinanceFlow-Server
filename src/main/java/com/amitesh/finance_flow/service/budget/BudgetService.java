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

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public ResponseEntity<?> createBudget(BudgetCreateRequest req) {

        Budget budget = Budget.builder()
                .budgetId(UUID.randomUUID().toString())
                .budgetName(req.getBudgetName())
                .budgetAmount(req.getBudgetAmount())
                .category(req.getCategory())
                .period(req.getPeriod())
                .startDate(req.getStartDate())
                .description(req.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        try {
            String userId = req.getUserId();
            UserBudgets userBudgets = repo.findByUserId(userId);
            if (userBudgets == null) {
                userBudgets = UserBudgets.builder()
                        .userId(userId)
                        .budgets(new ArrayList<>())
                        .build();
            }

            userBudgets.getBudgets().add(budget);
            repo.save(userBudgets);
            return ResponseEntity.status(HttpStatus.CREATED).body("Budget Successfully Created");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create budget" + e.getMessage());
        }

    }

    public ResponseEntity<?> deleteBudget(String budgetId, String userId) {
        try {
            UserBudgets userBudgets = repo.findByUserId(userId);
            if (userBudgets == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<Budget> budgets = userBudgets.getBudgets();

            Budget budgetToDelete = null;

            for (Budget budget : budgets) {
                if (budgetId.equals(budget.getBudgetId())) {
                    budgetToDelete = budget;
                    break;
                }
            }

            if (budgetToDelete == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Budget not found");
            }
            budgets.remove(budgetToDelete);

            repo.save(userBudgets);

            return ResponseEntity.status(HttpStatus.OK).body("Budget deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete Budget" + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllBudgets(String userId) {
        try {
            UserBudgets userBudgets = repo.findByUserId(userId);

            if (userBudgets == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<Budget> budgets = userBudgets.getBudgets();
            return ResponseEntity.status(HttpStatus.OK).body(budgets + "fetched all the budgets");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch all the goals" + e.getMessage());
        }
    }

    public Budget getBudget(String userId, String budgetId) {
        UserBudgets userBudgets = repo.findByUserId(userId);
        if (userBudgets == null) {
            throw new ResourceNotFoundException("Budget Not Found");

        }

        return userBudgets.getBudgets().stream()
                .filter(budget -> budget.getBudgetId().equals(budgetId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Budget Not Found With the BudgetID " + budgetId));


    }

    public ResponseEntity<?> updateBudget(String budgetId, BudgetCreateRequest req) {
        String userId = req.getUserId();
        try{
            UserBudgets userBudgets = repo.findByUserId(userId);
            if(userBudgets == null){
                throw new UserNotFoundException("User Not Found");
            }

            Budget budgetToUpdate = null;

            List<Budget> budgets = userBudgets.getBudgets();

            for(Budget budget : budgets){
                if(budget.getBudgetId().equals(budgetId)){
                    budgetToUpdate = budget;
                    break;
                }
            }

            if(budgetToUpdate == null){
                throw new ResourceNotFoundException("Budget Not Found");
            }

            budgetToUpdate.setBudgetName(req.getBudgetName());
            budgetToUpdate.setBudgetAmount(req.getBudgetAmount());
            budgetToUpdate.setCategory(req.getCategory());
            budgetToUpdate.setPeriod(req.getPeriod());
            budgetToUpdate.setDescription(req.getDescription());
            budgetToUpdate.setUpdatedAt(LocalDateTime.now());

            userBudgets.setBudgets(budgets);
            repo.save(userBudgets);

            return ResponseEntity.status(HttpStatus.OK).body("Budget Updated Successfully");
        }catch(Exception e ){
            throw new RuntimeException(e.getMessage());
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