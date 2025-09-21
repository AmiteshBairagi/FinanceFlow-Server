package com.amitesh.finance_flow.repo.goals;

import com.amitesh.finance_flow.model.goals.UserGoals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalsRepository extends MongoRepository<UserGoals,String> {
    UserGoals findByUserId(String userId);
}
