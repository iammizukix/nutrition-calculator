package com.food.youeat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 60, nullable = false)
    private String name;
    @Column(name = "password", length = 120, nullable = false)
    private String password;
    @Column(name = "role", length = 120)
    private String role;
    @Column(name = "status", nullable = false)
    private UserStatusEnum status;
}
