package com.amitesh.finance_flow.model.settings;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {
    private boolean budgetAlerts;
    private boolean billReminders;
    private boolean goalUpdates;
}
