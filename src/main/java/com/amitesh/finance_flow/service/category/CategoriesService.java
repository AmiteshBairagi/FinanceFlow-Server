package com.amitesh.finance_flow.service.category;

import com.amitesh.finance_flow.dto.category.CreateCategoryRequest;
import com.amitesh.finance_flow.model.categories.Category;
import com.amitesh.finance_flow.model.categories.UserCategories;
import com.amitesh.finance_flow.repo.categories.CategoriesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

@Service
public class CategoriesService {
    private final CategoriesRepository repo;

    public CategoriesService(CategoriesRepository repo) {
        this.repo = repo;
    }

    public ResponseEntity<?> createCategory(CreateCategoryRequest req, String userId) {
        try{
            Category category = Category.builder()
                    .categoryId(UUID.randomUUID().toString())
                    .categoryName(req.getCategoryName())
                    .type(req.getType())
                    .createdAt(Instant.now())
                    .updatedAt(Instant.now())
                    .build();


            UserCategories userCategories = repo.findByUserId(userId);
            if(userCategories == null){
                userCategories = new UserCategories();
                userCategories.setUserId(userId);
                userCategories.setCategories(new ArrayList<>(Arrays.asList(category)));
                userCategories.setCreatedAt(Instant.now());
            }else{
                userCategories.getCategories().add(category);
            }

            repo.save(userCategories);
            return ResponseEntity.status(HttpStatus.CREATED).body("Category Saved Successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
