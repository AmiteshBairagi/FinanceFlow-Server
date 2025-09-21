package com.amitesh.finance_flow.repo.expense;

import com.amitesh.finance_flow.model.expense.UserExpense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends MongoRepository<UserExpense,String> {
    UserExpense findByUserId(String userId);
}
