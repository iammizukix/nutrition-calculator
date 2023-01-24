package com.food.youeat.service;

import com.food.youeat.dto.HistorySearchConditionDto;
import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class HistoryService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<MealEntity> getMealsByUsername(String username) {
        log.info("getMealsByUsername: username={}", username);
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. username=" + username))
                .getMealList();
    }

    public List<MealEntity> getMealsBySearchCondition(String username, HistorySearchConditionDto condition) {
        log.info("getMealsBySearchCondition: username={}, condition={}", username, condition);
        List<MealEntity> userMeals = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. username=" + username))
                .getMealList();
        return filtered(userMeals, condition.getDate(), condition.getCategoryId(), condition.getKeyword());
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

}
