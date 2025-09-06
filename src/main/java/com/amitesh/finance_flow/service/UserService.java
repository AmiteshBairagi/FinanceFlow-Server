package com.amitesh.finance_flow.service;


import com.amitesh.finance_flow.dto.AllUsersResponse;
import com.amitesh.finance_flow.repo.AuthRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final AuthRepository authRepo;

    public UserService(AuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    public List<AllUsersResponse> getAllUsers() {
        return   authRepo.findAll()
                .stream()
                .map(user -> new AllUsersResponse(user.getId(),user.getFirstName(),user.getLastName(),user.getFullName(),user.getEmail()))
                .toList();
    }
}
