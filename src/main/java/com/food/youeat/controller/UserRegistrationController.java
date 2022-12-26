package com.food.youeat.controller;

import com.food.youeat.dto.RegistrationFormDto;
import com.food.youeat.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@Slf4j
public class UserRegistrationController {

    @Autowired
    UserRegistrationService userRegistrationService;

    @GetMapping("/input")
    public String input(Model model) {
        model.addAttribute("form", new RegistrationFormDto());
        return "registration/input";
    }

    @GetMapping("/confirm")
    public String confirm(
            Model model,
            @ModelAttribute RegistrationFormDto form
    ) {
        log.info("confirm : RegistrationFormDto={}", form);
        model.addAttribute("form", form);
        return "registration/confirm";
    }

    @PostMapping("/submit")
    public String submit(
//            Model model,
            @ModelAttribute RegistrationFormDto form
    ) {
        log.info("submit : RegistrationFormDto={}", form);
        userRegistrationService.registerUser(form.getUsername(), form.getPassword());
        log.info("user registration succeeded");
        return "redirect:/login";
    }

}