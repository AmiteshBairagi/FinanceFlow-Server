package com.amitesh.finance_flow.model.goals;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Goal {
    @Id
    private String goalId;
    private String goalName;
    private double targetAmount;
    private double currentAmount;
    private LocalDate deadline;
    private String description;
    @Builder.Default
    private String status = "active";
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
