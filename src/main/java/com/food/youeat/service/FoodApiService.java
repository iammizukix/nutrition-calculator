package com.food.youeat.service;

import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.exception.DataNotFoundException;
import com.food.youeat.repository.CalorieRepository;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.FoodRepository;
import com.food.youeat.repository.MealRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class FoodApiService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private CalorieRepository calorieRepository;
    @Autowired
    private MealRepository mealRepository;

    public List<FoodEntity> getAllFood() {
        return foodRepository.findAll();
    }

    public List<FoodEntity> getFoodByCategoryId(Integer categoryId) {
        CategoryEntity category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("category not found. categoryId=" + categoryId));
        return category.getFoodList();
    }

    public List<Integer> getDailyCalories() {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusDays(6);

        log.info("getDailyCalories: from={}, to={}", start, end);

        List<MealEntity> meals = mealRepository.findByHadAtBetween(start, end);

        List<Integer> dailyCalories = new ArrayList<>();
        while (!start.isAfter(end)) {
            LocalDate targetDate = start;
            AtomicInteger total = new AtomicInteger();
            meals.stream()
                    .filter(m -> m.getHadAt().isEqual(targetDate))
                    .forEach(m -> total.addAndGet(
                            // total calories = calories per gram * grams
                            m.getFood().getCalories() * m.getGram()
                    ));
            dailyCalories.add(total.get());
            start = start.plusDays(1);
        }
        return dailyCalories;
    }
}
