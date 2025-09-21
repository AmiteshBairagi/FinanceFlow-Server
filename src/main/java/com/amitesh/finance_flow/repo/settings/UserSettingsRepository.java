package com.amitesh.finance_flow.repo.settings;

import com.amitesh.finance_flow.model.settings.UserSettings;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSettingsRepository extends MongoRepository<UserSettings,String> {
    UserSettings findByUserId(String userId);
}
