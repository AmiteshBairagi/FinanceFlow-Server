package com.amitesh.finance_flow.dto;


import com.amitesh.finance_flow.model.Settings;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank
    @Email
    private String email;
    private String username;
    private String password;
    private String fullName;
    private String country;
    private String currency;


    private Date createdAt;

    private Settings settings;

}
