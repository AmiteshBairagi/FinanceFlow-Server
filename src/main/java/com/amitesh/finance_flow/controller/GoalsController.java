package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.CreateGoalRequest;
import com.amitesh.finance_flow.service.GoalsService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
public class GoalsController {
    private final GoalsService goalsService;

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGoal(@RequestBody CreateGoalRequest req){

        return goalsService.createGoal(req);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGoal(@RequestParam String userId,@RequestParam String goalId){
        return goalsService.deleteGoal(userId,goalId);
    }

    @GetMapping("/all-goals")
    public ResponseEntity<?> getAllGoals(@RequestParam String userId){
        return goalsService.getAllGoals(userId);
    }

}
