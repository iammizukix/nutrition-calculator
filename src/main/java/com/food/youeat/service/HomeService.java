package com.food.youeat.service;

import com.food.youeat.dto.MealFormDto;
import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.entity.UserEntity;
import com.food.youeat.exception.DataNotFoundException;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.FoodRepository;
import com.food.youeat.repository.MealRepository;
import com.food.youeat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class HomeService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepository foodRepository;

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    public void saveMeal(MealFormDto form, String username) {
        log.info("saveMeal: MealFormDto={}, username={}", form, username);
        UserEntity user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found. username = " + username));
        FoodEntity food = foodRepository.findById(form.getFoodId())
                .orElseThrow(() -> new DataNotFoundException("food not found. foodId = " + form.getFoodId()));
        MealEntity meal = new MealEntity();
        meal.setUser(user);
        meal.setFood(food);
        meal.setGram(form.getQuantity());
        meal.setDateTime(new Date());
        mealRepository.save(meal);
    }


}
