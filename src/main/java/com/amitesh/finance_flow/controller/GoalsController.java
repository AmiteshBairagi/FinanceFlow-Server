package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.CreateGoalRequest;
import com.amitesh.finance_flow.service.GoalsService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
public class GoalsController {
    private final GoalsService goalsService;

    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGoal(@RequestBody CreateGoalRequest req){
        return goalsService.createGoal(req);
    }
}
