package com.amitesh.finance_flow.model.categories;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user_categories")
@Builder
public class UserCategories {
    @Id
    private String id;
    private String userId;
    private List<Category> categories;
    private Instant createdAt;
}
