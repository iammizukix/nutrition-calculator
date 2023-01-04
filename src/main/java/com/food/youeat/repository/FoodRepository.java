package com.food.youeat.repository;

import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findByCategory(CategoryEntity category);
}