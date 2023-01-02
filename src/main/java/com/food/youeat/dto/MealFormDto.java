package com.food.youeat.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MealFormDto {
    @NonNull
    Integer foodId;
    @NonNull
    Integer quantity;
}
