package com.food.youeat.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealApiDto {
    Integer categoryId;
    String foodName;
    Integer quantity;
    LocalDate date;
    LocalTime time;
}
