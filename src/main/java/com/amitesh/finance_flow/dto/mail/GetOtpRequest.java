package com.amitesh.finance_flow.dto.mail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOtpRequest {
    private String email;
    private String password;
}
