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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return Optional.of(foodRepository.findByCategory(category))
                .orElseThrow(() -> new DataNotFoundException("food not found. category=" + category.toString()));
    }

    public List<Integer> getDailyCalories() {
        LocalDate today = LocalDate.now();

        LocalDate sunday = today.minusWeeks(1).with(DayOfWeek.SUNDAY);
        LocalDate saturday = today.with(DayOfWeek.SATURDAY);
        log.info("getDailyCalories: from={}, to={}", sunday, saturday);

        List<MealEntity> meals = mealRepository.findByHadAtBetween(sunday, saturday);

        List<Integer> dailyCalories = new ArrayList<>();
        while (!sunday.isAfter(saturday)) {
            LocalDate targetDate = sunday;
            AtomicInteger total = new AtomicInteger();
            meals.stream()
                    .filter(m -> m.getHadAt().isEqual(targetDate))
                    .forEach(m -> total.addAndGet(
                            // total calories = calories per gram * grams
                            m.getFood().getCalories() * m.getGram()
                    ));
            dailyCalories.add(total.get());
            sunday = sunday.plusDays(1);
        }
        return dailyCalories;
    }
}
