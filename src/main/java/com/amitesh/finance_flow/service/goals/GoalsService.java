package com.amitesh.finance_flow.service.goals;

import com.amitesh.finance_flow.customException.ResourceNotFoundException;
import com.amitesh.finance_flow.dto.goal.CreateGoalRequest;
import com.amitesh.finance_flow.model.goals.Goal;
import com.amitesh.finance_flow.model.goals.UserGoals;
import com.amitesh.finance_flow.repo.goals.GoalsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class GoalsService {
    private final GoalsRepository repo;

    public GoalsService(GoalsRepository repo) {
        this.repo = repo;
    }


    public ResponseEntity<?> createGoal(CreateGoalRequest req, String userId) {
        Goal goal = Goal.builder()
                .goalId(UUID.randomUUID().toString())
                .goalName(req.getGoalName())
                .targetAmount(req.getTargetAmount())
                .currentAmount(req.getCurrentAmount())
                .deadline(req.getDeadline())
                .description(req.getDecription())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        try{
            UserGoals userGoals = repo.findByUserId(userId);
            if(userGoals == null){
                userGoals = UserGoals.builder()
                        .userId(userId)
                        .goals(Arrays.asList(goal))
                        .createdAt(Instant.now())
                        .build();
                repo.save(userGoals);
                return ResponseEntity.status(HttpStatus.OK).body("Goal Saved Successfully");
            }else{
                userGoals.getGoals().add(goal);
                repo.save(userGoals);
                return ResponseEntity.status(HttpStatus.OK).body("Goals saved successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<?> getAllGoals(String userId) {
        try{

            UserGoals userGoals = repo.findByUserId(userId);
            if(userGoals == null){
                throw new ResourceNotFoundException("No goals found for the user");
            } else if (userGoals.getGoals().isEmpty()) {
                throw new ResourceNotFoundException("No goals found for the user");
            }else{
                List<Goal> goals = userGoals.getGoals();
                return ResponseEntity.status(HttpStatus.OK).body(goals);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
