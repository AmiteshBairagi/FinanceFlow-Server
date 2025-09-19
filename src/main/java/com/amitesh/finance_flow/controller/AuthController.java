package com.amitesh.finance_flow.controller;


import com.amitesh.finance_flow.dto.auth.ChangePasswordRequest;
import com.amitesh.finance_flow.dto.auth.UserCreateRequest;
import com.amitesh.finance_flow.dto.auth.UserLoginRequest;
import com.amitesh.finance_flow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/user")
public class AuthController {
    private final AuthService authService;
    public AuthController( AuthService authService) {
        this.authService = authService;

    }

    @GetMapping("/hi")
    public String greet (){
        return "Amitesh";
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequest req) {
        return authService.createUser(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest req){
        return authService.loginUser(req);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest req){
        return authService.changePassword(req);
    }


}
