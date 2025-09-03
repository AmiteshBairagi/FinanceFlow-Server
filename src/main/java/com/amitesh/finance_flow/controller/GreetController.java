package com.amitesh.finance_flow.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

    @GetMapping("/")
    public String greet(){
        return "Wellcome to FinanceFlow";
    }
}
