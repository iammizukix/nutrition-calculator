package com.food.youeat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class MealApiDto {
    Integer categoryId;
    Integer foodId;
    Integer quantity;
    LocalDate date;
    LocalTime time;
}
