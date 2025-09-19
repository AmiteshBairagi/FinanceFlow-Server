package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.goal.CreateGoalRequest;
import com.amitesh.finance_flow.service.GoalsService;
import com.amitesh.finance_flow.service.UserPrincipal;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
public class GoalsController {
    private final GoalsService goalsService;

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }
    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGoal(@RequestBody CreateGoalRequest req){
        return goalsService.createGoal(req);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGoal(@RequestParam String goalId){
        return goalsService.deleteGoal(getCurrentUser().getUserId(), goalId);
    }

    @GetMapping("/all-goals")
    public ResponseEntity<?> getAllGoals(){

        return goalsService.getAllGoals(getCurrentUser().getUserId());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGoal(@RequestParam String goalId, @RequestBody CreateGoalRequest req){
        return goalsService.updateGoal(goalId,req);
    }

    @GetMapping()
    public ResponseEntity<?> getGoal(@RequestParam String goalId){
        return goalsService.getGoal(getCurrentUser().getUserId(), goalId);
    }
}
