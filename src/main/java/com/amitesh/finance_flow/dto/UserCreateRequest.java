package com.amitesh.finance_flow.dto;


import com.amitesh.finance_flow.model.Settings;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    @NotBlank
    @Email
    private String email;
    private String password;

}
