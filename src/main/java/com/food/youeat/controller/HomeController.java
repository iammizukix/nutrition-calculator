package com.food.youeat.controller;

import com.food.youeat.dto.MealFormDto;
import com.food.youeat.entity.UserDetailsImpl;
import com.food.youeat.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@Validated
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("/index")
    public String index(
            Model model,
            @ModelAttribute("message") String message
    ) {
        log.info("index");
        model.addAttribute("categories", homeService.getAllCategories());
        model.addAttribute("form", new MealFormDto());
        model.addAttribute("message", message);
        return "home";
    }

    @PostMapping("/input")
    public String input(
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetailsImpl user,
            @ModelAttribute MealFormDto form
    ) {
        log.info("input: User={}, MealFormDto={}", user, form);
        homeService.saveMeal(form, user.getUsername());
        redirectAttributes.addAttribute("message", "Saving has succeeded.");
        return "redirect:/home/index";
    }

    @GetMapping("/history")
    public String history(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        log.info("history");
        model.addAttribute("meals", homeService.getMealsByUsername(user.getUsername()));
        return "history";
    }

}