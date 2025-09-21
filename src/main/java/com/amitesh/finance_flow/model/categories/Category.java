package com.amitesh.finance_flow.model.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private String categoryId;
    private String categoryName;
    private String type;     // Default or Custom
    private Instant createdAt;
    private Instant updatedAt;
}
