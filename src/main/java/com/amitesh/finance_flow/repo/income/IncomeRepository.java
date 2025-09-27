package com.amitesh.finance_flow.repo.income;

import com.amitesh.finance_flow.model.income.UserIncome;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends MongoRepository<UserIncome,String> {
    UserIncome findByUserIdAndYearAndMonth(String userId, int year, int month);
}
