package com.amitesh.finance_flow.repo;

import com.amitesh.finance_flow.model.goals.Goal;
import com.amitesh.finance_flow.model.goals.UserGoals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends MongoRepository<UserGoals,String> {
    UserGoals findByUserId(String userId);
}
