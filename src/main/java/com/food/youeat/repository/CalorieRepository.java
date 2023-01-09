package com.food.youeat.repository;

import com.food.youeat.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalorieRepository extends JpaRepository<CategoryEntity, Integer> {
}