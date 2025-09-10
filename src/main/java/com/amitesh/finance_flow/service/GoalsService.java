package com.amitesh.finance_flow.service;

import com.amitesh.finance_flow.dto.CreateGoalRequest;
import com.amitesh.finance_flow.model.goals.Goal;
import com.amitesh.finance_flow.model.goals.UserGoals;
import com.amitesh.finance_flow.repo.GoalsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class GoalsService {
    private final GoalsRepository repo;

    public GoalsService(GoalsRepository repo) {
        this.repo = repo;
    }


    public ResponseEntity<?> createGoal(CreateGoalRequest req) {
        UserGoals document = repo.findByUserId(req.getUserId());

        if(document == null){
            document = UserGoals.builder()
                    .userId(req.getUserId())
                    .goals(new ArrayList<>())
                    .build();
        }

        Goal goal = Goal.builder()
                .goalName(req.getGoalName())
                .targetAmount(req.getTargetAmount())
                .currentAmount(req.getCurrentAmount())
                .deadline(req.getDeadline())
                .description(req.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();


        document.getGoals().add(goal);

        try {
            repo.save(document);
            return ResponseEntity.status(HttpStatus.OK).body("Goal Successfully Saved in the DB");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save goal in the DB" + e.getMessage());
        }
    }
}
