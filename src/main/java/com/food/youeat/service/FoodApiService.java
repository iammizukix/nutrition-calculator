package com.food.youeat.service;

import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.exception.DataNotFoundException;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FoodApiService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FoodRepository foodRepository;

    public List<FoodEntity> getAllFood() {
        return foodRepository.findAll();
    }

    public List<FoodEntity> getFoodByCategoryId(Integer categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("category not found. categoryId=" + categoryId));
        return Optional.of(foodRepository.findByCategory(category))
                .orElseThrow(() -> new DataNotFoundException("food not found. category=" + category.toString()));
    }
}
