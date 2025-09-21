package com.amitesh.finance_flow.repo.budgets;


import com.amitesh.finance_flow.model.budgets.UserBudgets;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface BudgetRepository extends MongoRepository<UserBudgets,String> {
    UserBudgets findByUserId(String userId);
}
