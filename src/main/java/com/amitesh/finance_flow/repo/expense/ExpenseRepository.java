package com.amitesh.finance_flow.repo.expense;

import com.amitesh.finance_flow.model.expense.UserExpense;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<UserExpense,String> {
    List<UserExpense> findByUserId(String userId);

    UserExpense findByUserIdAndYearAndMonth(String userId, int year, int month);

    List<UserExpense> findByUserIdAndYear(String userId, int year);
}
