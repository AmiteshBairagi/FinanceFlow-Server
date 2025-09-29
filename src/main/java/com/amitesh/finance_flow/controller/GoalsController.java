package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.goal.CreateGoalRequest;
import com.amitesh.finance_flow.service.goals.GoalsService;
import com.amitesh.finance_flow.service.UserPrincipal;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
public class GoalsController {
    private final GoalsService service;

    public GoalsController(GoalsService service) {
        this.service = service;
    }


    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGoal(@RequestBody CreateGoalRequest req){
        return service.createGoal(req,getCurrentUser().getUserId());
    }

    @GetMapping("/all-goals")
    public ResponseEntity<?> getAllGoals(){
        return service.getAllGoals(getCurrentUser().getUserId());
    }
}
