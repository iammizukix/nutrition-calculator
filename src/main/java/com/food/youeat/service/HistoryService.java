package com.food.youeat.service;

import com.food.youeat.dto.HistorySearchConditionDto;
import com.food.youeat.dto.MealFormDto;
import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.exception.DataNotFoundException;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.FoodRepository;
import com.food.youeat.repository.MealRepository;
import com.food.youeat.repository.UserRepository;
import com.food.youeat.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class HistoryService {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MealRepository mealRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Page<MealEntity> getMealsByUsername(Pageable pageable, String username) {
        log.info("getMealsByUsername: username={}", username);
        List<MealEntity> meals = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. username=" + username))
                .getMealList();
        return paginateList(pageable, meals);
    }

    public Page<MealEntity> getMealsBySearchCondition(Pageable pageable, String username, HistorySearchConditionDto condition) {
        log.info("getMealsBySearchCondition: username={}, condition={}", username, condition);
        List<MealEntity> userMeals = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. username=" + username))
                .getMealList();
        userMeals = filtered(userMeals, condition.getDate(), condition.getCategoryId(), condition.getKeyword());
        return paginateList(pageable, userMeals);
    }

    private List<MealEntity> filtered(List<MealEntity> meals, LocalDate date, Integer categoryId, String keyword) {
        Stream<MealEntity> mealsStream = meals.stream();
        if (Objects.nonNull(date)) {
            mealsStream = mealsStream.filter(m -> m.getHadAt().equals(date));
        }
        if (Objects.nonNull(categoryId)) {
            mealsStream = mealsStream.filter(m -> m.getFood().getCategory().getId().equals(categoryId));
        }
        if (Objects.nonNull(keyword)) {
            // ignore case
            mealsStream = mealsStream.filter(m -> m.getFood().getName().toLowerCase().contains(keyword.toLowerCase()));
        }
        return mealsStream.toList();
    }

    private <T> Page<T> paginateList(Pageable pageable, List<T> list) {
        int first = Math.min(Long.valueOf(pageable.getOffset()).intValue(), list.size());
        int last = Math.min(first + pageable.getPageSize(), list.size());
        return new PageImpl<>(list.subList(first, last), pageable, list.size());
    }

    @Transactional
    public void updateMeal(MealFormDto form) {
        log.info("deleteMealByMealId: MealFormDto={}", form);
        MealEntity meal = mealRepository.findById(form.getMealId()).orElseThrow(() -> new DataNotFoundException("Meal not found. mealId=" + form.getMealId()));
        FoodEntity food = foodRepository.findById(form.getFoodId()).orElseThrow(() -> new DataNotFoundException("Food not found. foodId=" + form.getFoodId()));
        meal.setFood(food);
        meal.setGram(form.getQuantity());
        meal.setHadAt(DateUtils.toLocalDate(form.getDate()));
        meal.setHadOn(DateUtils.toLocalTime(form.getTime()));
        mealRepository.save(meal);
    }

    @Transactional
    public void deleteMealByMealId(Long mealId) {
        log.info("deleteMealByMealId: mealId={}", mealId);
        MealEntity meal = mealRepository.findById(mealId).orElseThrow(() -> new DataNotFoundException("Meal not found. mealId=" + mealId));
        mealRepository.delete(meal);
    }
}
