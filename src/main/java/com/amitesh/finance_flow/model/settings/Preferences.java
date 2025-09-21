package com.amitesh.finance_flow.model.settings;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Preferences {
    private String currencyCode;
    private String currencySymbol;
    private String dateFormat;
}
