package com.amitesh.finance_flow.model.user;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;

}
