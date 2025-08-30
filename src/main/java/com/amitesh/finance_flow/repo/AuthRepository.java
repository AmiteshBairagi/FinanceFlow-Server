package com.amitesh.finance_flow.repo;


import com.amitesh.finance_flow.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends MongoRepository<User, String> {
    boolean existsByEmail(String email);
}

