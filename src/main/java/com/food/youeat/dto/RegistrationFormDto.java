package com.food.youeat.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RegistrationFormDto {
    @NonNull
    String username;
    @NonNull
    String password;
}
