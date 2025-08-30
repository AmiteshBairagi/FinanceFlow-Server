package com.amitesh.finance_flow.controller;


import com.amitesh.finance_flow.dto.UserCreateRequest;
import com.amitesh.finance_flow.dto.UserLoginRequest;
import com.amitesh.finance_flow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/users")
public class AuthController {
    private final AuthService userService;
    public AuthController(AuthService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequest req) {
        return userService.createUser(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest req){
        return userService.loginUser(req);
    }
}
