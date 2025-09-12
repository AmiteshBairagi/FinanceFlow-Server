package com.amitesh.finance_flow.service;

import com.amitesh.finance_flow.dto.CreateGoalRequest;
import com.amitesh.finance_flow.model.goals.Goal;
import com.amitesh.finance_flow.model.goals.UserGoals;
import com.amitesh.finance_flow.repo.GoalsRepository;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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
                .goalId(UUID.randomUUID().toString())
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

    public ResponseEntity<?> deleteGoal(String userId,String goalId) {
        List<Goal> existingGoals = repo.findByUserId(userId).getGoals();

        Goal goalToDelete = null;

        for(Goal goal : existingGoals){
            if(goal.getGoalId().equals(goalId)){
                goalToDelete = goal;
                break;
            }
        }

        if(goalToDelete == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No goal found to delete");
        }else{
            existingGoals.remove(goalToDelete);
        }

        return  ResponseEntity.status(HttpStatus.OK).body("Goal deleted successfully");
    }

    public ResponseEntity<?> getAllGoals(String userId) {
        try{
            UserGoals existingUserGoals = repo.findByUserId(userId);
            if(existingUserGoals == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
            }

            List<Goal> goals = existingUserGoals.getGoals();

            if(goals.isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body("No goals found for this user");
            }else{
                return ResponseEntity.status(HttpStatus.OK).body(goals);
            }
        }catch(Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error getting user goals" + e.getMessage());
        }


    }

    public ResponseEntity<?> updateGoal(String goalId, CreateGoalRequest req) {
        try{
            String userId = req.getUserId();
            UserGoals userGoals  = repo.findByUserId(userId);
            List<Goal> goals = userGoals.getGoals();
            Goal goalToUpdate = null;

            for(Goal goal : goals){
                if(goal.getGoalId().equals(goalId)){
                    goalToUpdate = goal;
                    break;
                }
            }

            if(goalToUpdate == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No goal found to update");
            }

            goalToUpdate.setGoalName(req.getGoalName());
            goalToUpdate.setTargetAmount(req.getTargetAmount());
            goalToUpdate.setCurrentAmount(req.getCurrentAmount());
            goalToUpdate.setDeadline(req.getDeadline());
            goalToUpdate.setDescription(req.getDescription());
            goalToUpdate.setUpdatedAt(LocalDateTime.now());

            userGoals.setGoals(goals);
            repo.save(userGoals);

            return  ResponseEntity.status(HttpStatus.OK).body("Goal Updated Successfully");
        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update goals" + e.getMessage());
        }
    }
}
