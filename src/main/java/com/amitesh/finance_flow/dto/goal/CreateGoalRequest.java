package com.amitesh.finance_flow.dto.goal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoalRequest {
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private Instant deadline;
    private String decription;
    private String status;
}
