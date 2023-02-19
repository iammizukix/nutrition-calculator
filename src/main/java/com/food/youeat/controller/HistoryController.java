package com.food.youeat.controller;

import com.food.youeat.dto.HistorySearchConditionDto;
import com.food.youeat.dto.MealFormDto;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.entity.UserDetailsImpl;
import com.food.youeat.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping
    public String history(
            Model model,
            Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        log.info("history: user={}", user);
        Page<MealEntity> mealsPage = historyService.getMealsByUsername(pageable, user.getUsername());
        model.addAttribute("categories", historyService.getAllCategories());
        model.addAttribute("page", mealsPage);
        model.addAttribute("meals", mealsPage.getContent());
        model.addAttribute("condition", new HistorySearchConditionDto());
        model.addAttribute("form", new MealFormDto());
        return "history";
    }

    @GetMapping("/search")
    public String search(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl user,
            Pageable pageable,
            HistorySearchConditionDto condition
    ) {
        log.info("search: user={}, HistorySearchConditionDto={}", user, condition);
        model.addAttribute("categories", historyService.getAllCategories());
        Page<MealEntity> mealsPage = historyService.getMealsBySearchCondition(pageable, user.getUsername(), condition);
        model.addAttribute("page", mealsPage);
        model.addAttribute("meals", mealsPage.getContent());
        model.addAttribute("condition", condition);
        return "history";
    }

    @PostMapping("/edit")
    public String edit(
            @ModelAttribute MealFormDto form
    ) {
        log.info("edit: MealFormDto={}", form);
        historyService.updateMeal(form);
        return "redirect:/history";
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam("mealId") Long mealId
    ) {
        log.info("delete: mealId={}", mealId);
        historyService.deleteMealByMealId(mealId);
        return "redirect:/history";
    }
}