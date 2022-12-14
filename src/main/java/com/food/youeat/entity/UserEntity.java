package com.food.youeat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 10, nullable = false)
    private String name;
    @Column(name = "password", length = 120, nullable = false)
    private String password;
    @Column(name = "role", length = 10)
    private String role;
    @Column(name = "status", nullable = false)
    private UserStatusEnum status;
    @OneToMany(mappedBy = "user")
    private List<MealEntity> mealList;
}
