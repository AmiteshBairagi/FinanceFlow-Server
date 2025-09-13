package com.amitesh.finance_flow.repo;


import com.amitesh.finance_flow.model.budgets.UserBudget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface BudgetRepository extends MongoRepository<UserBudget,String> {
    UserBudget findByUserId(String userId);
}
