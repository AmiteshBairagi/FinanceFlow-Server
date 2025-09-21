package com.amitesh.finance_flow.service.settings;

import com.amitesh.finance_flow.dto.settings.AddSettingsRequest;
import com.amitesh.finance_flow.model.settings.Notifications;
import com.amitesh.finance_flow.model.settings.Preferences;
import com.amitesh.finance_flow.model.settings.UserSettings;
import com.amitesh.finance_flow.model.user.User;
import com.amitesh.finance_flow.repo.settings.UserSettingsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class UserSettingsService {
    private final UserSettingsRepository repo;

    public UserSettingsService(UserSettingsRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> addSettings(AddSettingsRequest req, String userId) {
        try{
            Preferences preferences = Preferences.builder()
                    .currencyCode(req.getCurrencyCode())
                    .currencySymbol(req.getCurrencySymbol())
                    .dateFormat(req.getDateFormat())
                    .build();

            Notifications notifications = Notifications.builder()
                    .budgetAlerts(req.isBudgetAlerts())
                    .billReminders(req.isBillReminders())
                    .goalUpdates(req.isGoalUpdates())
                    .build();

            UserSettings userSettings = repo.findByUserId(userId);
            if(userSettings == null){
                userSettings = UserSettings.builder()
                        .userId(userId)
                        .preferences(preferences)
                        .notifications(notifications)
                        .createdAt(Instant.now())
                        .updatedAt(Instant.now())
                        .build();
            }else{
                userSettings.getPreferences().setCurrencyCode(req.getCurrencyCode());
                userSettings.getPreferences().setCurrencySymbol(req.getCurrencySymbol());
                userSettings.getPreferences().setDateFormat(req.getDateFormat());

                userSettings.getNotifications().setBillReminders(req.isBillReminders());
                userSettings.getNotifications().setBudgetAlerts(req.isBudgetAlerts());
                userSettings.getNotifications().setGoalUpdates(req.isGoalUpdates());

                userSettings.setUpdatedAt(Instant.now());
            }

            repo.save(userSettings);

            return ResponseEntity.status(HttpStatus.OK).body("Settings Saved Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
