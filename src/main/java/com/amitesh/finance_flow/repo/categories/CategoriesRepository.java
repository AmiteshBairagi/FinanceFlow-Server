package com.amitesh.finance_flow.repo.categories;

import com.amitesh.finance_flow.model.categories.UserCategories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends MongoRepository<UserCategories,String> {
    UserCategories findByUserId(String userId);
}
