package com.amitesh.finance_flow.model.goals;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "user_goals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGoals {

    @Id
    private String id;
    private String userId;
    private List<Goal> goals = new ArrayList<>();


}
