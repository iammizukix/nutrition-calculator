package com.food.youeat.controller;

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

@Controller
@Slf4j
@RequestMapping("/api")
public class FoodApiController {

    @Autowired
    FoodApiService foodApiService;

    @GetMapping("/food")
    @ResponseBody
    public List<FoodEntity> getAllFood() {
        log.info("getAllFood");
        return foodApiService.getAllFood();
    }

    @GetMapping("/food/{categoryId}")
    @ResponseBody
    public List<FoodEntity> getAllFoodByCategoryId(
            @PathVariable int categoryId
    ) {
        log.info("getAllFoodByCategoryId: categoryId={}", categoryId);
        return foodApiService.getFoodByCategoryId(categoryId);
    }


}