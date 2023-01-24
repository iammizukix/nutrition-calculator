package com.food.youeat.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {

    INACTIVE(0, "inactive"),
    ACTIVE(1, "active"),
    Forbidden(9, "forbidden");

    private final Integer id;
    private final String status;

    public UserStatusEnum getById(Integer id) {
        return Arrays.stream(UserStatusEnum.values())
                .filter(list -> list.getId().equals(id))
                .findFirst()
                .orElse(Forbidden);
    }
}
