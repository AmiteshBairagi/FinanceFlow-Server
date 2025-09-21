package com.amitesh.finance_flow.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddSettingsRequest {
    private String currencyCode;
    private String currencySymbol;
    private String dateFormat;
    private boolean budgetAlerts;
    private boolean billReminders;
    private boolean goalUpdates;
}
