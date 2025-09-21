package com.amitesh.finance_flow.controller;


import com.amitesh.finance_flow.dto.settings.AddSettingsRequest;
import com.amitesh.finance_flow.service.UserPrincipal;
import com.amitesh.finance_flow.service.settings.UserSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settings")
public class UserSettingsController {
    private final UserSettingsService service;

    public UserSettingsController(UserSettingsService service) {
        this.service = service;
    }

    private UserPrincipal getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserPrincipal) authentication.getPrincipal();
    }

    @PostMapping("/create")
    public ResponseEntity<?> addSettings(@RequestBody AddSettingsRequest req){
        return service.addSettings(req,getCurrentUser().getUserId());
    }
}
