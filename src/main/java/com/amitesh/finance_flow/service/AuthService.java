package com.amitesh.finance_flow.service;


import com.amitesh.finance_flow.dto.ChangePasswordRequest;
import com.amitesh.finance_flow.dto.UserCreateRequest;
import com.amitesh.finance_flow.dto.UserLoginRequest;
import com.amitesh.finance_flow.model.User;
import com.amitesh.finance_flow.repo.AuthRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    private final AuthRepository authRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(AuthRepository authRepo, AuthenticationManager authenticationManager, JWTService jwtService){
        this.authRepo = authRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public ResponseEntity<?> createUser(@Valid UserCreateRequest req) {
        if(authRepo.existsByEmail(req.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email Already Exists");
        }

        User newUser = new User();

        newUser.setEmail(req.getEmail());
        newUser.setFullName(req.getFullName());
        newUser.setCountry(req.getCountry());
        newUser.setCreatedAt(req.getCreatedAt());
        newUser.setSettings(req.getSettings());
        newUser.setPassword(encoder.encode(req.getPassword()));
        newUser.setUsername(req.getUsername());

        try {
            User savedUser = authRepo.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body("User Saved In The Database Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Document According to DB Validator" + e.getMessage());
        }

    }


    public ResponseEntity<?> loginUser(@Valid UserLoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));

        if(authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.OK).body(jwtService.getJwtToken(req.getUsername()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to login , bad credentials");

    }

    public ResponseEntity<?> changePassword(@Valid ChangePasswordRequest req) {

        String newPassword = req.getNewPassword();
        if(newPassword == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password cannot be null");
        }

        User user = authRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));


        user.setPassword(newPassword);
        authRepo.save(user);

        return ResponseEntity.status(HttpStatus.OK).body("Password Updated Successfully");
    }
}
