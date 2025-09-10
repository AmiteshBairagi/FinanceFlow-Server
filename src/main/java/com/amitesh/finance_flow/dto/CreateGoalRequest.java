package com.amitesh.finance_flow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoalRequest {
    private String userId;
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private String category;
    private LocalDate deadline;
    private String description;
}
