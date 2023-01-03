package com.food.youeat.service;

import com.food.youeat.dto.MealFormDto;
import com.food.youeat.entity.*;
import com.food.youeat.exception.DataNotFoundException;
import com.food.youeat.repository.CategoryRepository;
import com.food.youeat.repository.FoodRepository;
import com.food.youeat.repository.MealRepository;
import com.food.youeat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        mealRepository.save(meal);
    }


}
