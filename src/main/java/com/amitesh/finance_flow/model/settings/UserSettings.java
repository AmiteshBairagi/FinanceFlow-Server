package com.amitesh.finance_flow.model.settings;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_settings")
@Builder
public class UserSettings {
    @Id
    private String id;
    private String userId;
    private Preferences preferences;
    private Notifications notifications;
    private Instant createdAt;
    private Instant updatedAt;

}
