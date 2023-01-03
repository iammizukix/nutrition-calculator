package com.food.youeat.repository;

import com.food.youeat.entity.CategoryEntity;
import com.food.youeat.entity.FoodEntity;
import com.food.youeat.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
    List<FoodEntity> findByCategory(CategoryEntity category);
}