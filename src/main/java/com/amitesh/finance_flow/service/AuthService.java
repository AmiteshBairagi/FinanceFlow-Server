package com.amitesh.finance_flow.service;


import com.amitesh.finance_flow.dto.UserCreateRequest;
import com.amitesh.finance_flow.dto.UserLoginRequest;
import com.amitesh.finance_flow.model.User;
import com.amitesh.finance_flow.repo.AuthRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AuthService {

    private final AuthRepository userRepo;

    public AuthService(AuthRepository userRepo){
        this.userRepo = userRepo;
    }


    public ResponseEntity<?> createUser(@Valid UserCreateRequest req) {
        if(userRepo.existsByEmail(req.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists");
        }

        User newUser = new User();

        newUser.setEmail(req.getEmail());
        newUser.setFullName(req.getFullName());
        newUser.setCountry(req.getCountry());
        newUser.setCreatedAt(req.getCreatedAt());
        newUser.setSettings(req.getSettings());
        newUser.setPassword(req.getPassword());

        try {
            User savedUser = userRepo.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body("User Saved In The Database Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Document According to DB Validator" + e.getMessage());
        }

    }


    public ResponseEntity<?> loginUser(@Valid UserLoginRequest req) {
        String userPassword = req.getPassword();
        String userEmail = req.getEmail();

        if(!userRepo.existsByEmail(userEmail)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Not Exists");
        }

        User dbStoredUser = userRepo.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        if(dbStoredUser.getPassword().equals(userPassword)){
            return ResponseEntity.status(HttpStatus.OK).body("User Successfully Logged In");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password Incorrect");
        }

    }
}
