package com.amitesh.finance_flow.controller;

import com.amitesh.finance_flow.dto.AllUsersResponse;
import com.amitesh.finance_flow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<AllUsersResponse> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
