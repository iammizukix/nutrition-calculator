package com.food.youeat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "meals")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    @Column(name = "gram", nullable = false)
    private int gram;

    //String or Date?
    // date time user had food
    @Column(name = "date_time", nullable = false)
    private Date dateTime;
}
