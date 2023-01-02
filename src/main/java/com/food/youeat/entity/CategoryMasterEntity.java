package com.food.youeat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Table(name = "category_master")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", length = 10, nullable = false)
    private String name;
    @OneToMany(mappedBy = "category")
    private List<FoodMasterEntity> foodList;
}
