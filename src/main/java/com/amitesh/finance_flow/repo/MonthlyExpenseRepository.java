package com.amitesh.finance_flow.repo;

import com.amitesh.finance_flow.model.monthly_expense.MonthlyExpense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthlyExpenseRepository extends MongoRepository<MonthlyExpense,String> {

    MonthlyExpense findByUserIdAndYearAndMonth(String userId, int year, int month);
}
