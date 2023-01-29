package com.food.youeat.controller;

import com.food.youeat.dto.MealApiDto;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.service.FoodApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/api")
public class FoodApiController {

    @Autowired
    FoodApiService foodApiService;

    @GetMapping({"/food", "/food/{categoryId}"})
    @ResponseBody
    public List<FoodEntity> getFoodByCategoryId(
            @PathVariable(required = false) Integer categoryId
    ) {
        log.info("getFoodByCategoryId: categoryId={}", categoryId);
        if (Objects.isNull(categoryId)) {
            return foodApiService.getAllFood();
        }
        return foodApiService.getFoodByCategoryId(categoryId);
    }

    @GetMapping("/calories")
    @ResponseBody
    public List<Integer> getDailyCalories() {
        return foodApiService.getDailyCalories();
    }

    @GetMapping("/meal/{mealId}")
    @ResponseBody
    public MealApiDto getMealByMealId(
            @PathVariable Long mealId
    ) {
        return foodApiService.getMealByMealId(mealId);
    }

}