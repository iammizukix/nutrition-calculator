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

    private final int id;
    private final String status;

    public UserStatusEnum getById(int id) {
        return Arrays.stream(UserStatusEnum.values())
                .filter(list -> list.getId() == id)
                .findFirst()
                .orElse(Forbidden);
    }
}
