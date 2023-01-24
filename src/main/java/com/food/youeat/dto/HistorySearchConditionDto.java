package com.food.youeat.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class HistorySearchConditionDto {

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    LocalDate date;

    Integer categoryId;

    String keyword;

}
