package com.amitesh.finance_flow.repo.paymentMethod;

import com.amitesh.finance_flow.model.paymentMethod.UserPaymentMethods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends MongoRepository<UserPaymentMethods,String> {
    UserPaymentMethods findByUserId(String userId);
}
