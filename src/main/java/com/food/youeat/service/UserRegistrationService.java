package com.food.youeat.service;

import com.food.youeat.entity.UserEntity;
import com.food.youeat.entity.UserStatusEnum;
import com.food.youeat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUser(String name, String password, String role) {
        UserEntity user = new UserEntity();

        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setStatus(UserStatusEnum.ACTIVE);
        userRepository.save(user);
    }

}
