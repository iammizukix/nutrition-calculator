package com.food.youeat.controller;

import com.food.youeat.service.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("/input")
    public String input() {
        return "registration/input";
    }

    @PostMapping("/submit")
    public String submit(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String role
    ) {
        userRegistrationService.registerUser(username, password, role);
        return "redirect:/login";
    }

}