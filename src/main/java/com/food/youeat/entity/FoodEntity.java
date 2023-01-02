package com.food.youeat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "food")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    @OneToMany(mappedBy = "food")
    private List<MealEntity> mealList;
}