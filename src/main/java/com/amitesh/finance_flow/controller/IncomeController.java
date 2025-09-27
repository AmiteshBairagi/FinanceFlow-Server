package com.amitesh.finance_flow.controller;


import com.amitesh.finance_flow.dto.income.AddIncomeRequest;
import com.amitesh.finance_flow.service.UserPrincipal;
import com.amitesh.finance_flow.service.income.IncomeService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    private final IncomeService service;
    public IncomeController(IncomeService service) {
        this.service = service;
    }

    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addIncome(@RequestBody AddIncomeRequest req){
        return service.addIncome(req,getCurrentUser().getUserId());
    }


}
