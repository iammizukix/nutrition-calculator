package com.food.youeat.controller;

import com.food.youeat.dto.HistorySearchConditionDto;
import com.food.youeat.entity.MealEntity;
import com.food.youeat.entity.UserDetailsImpl;
import com.food.youeat.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @GetMapping
    public String history(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl user
    ) {
        log.info("history: user={}", user);
        model.addAttribute("categories", historyService.getAllCategories());
        model.addAttribute("meals", historyService.getMealsByUsername(user.getUsername()));
        model.addAttribute("condition", new HistorySearchConditionDto());
        return "history";
    }

    @GetMapping("/search")
    public String search(
            Model model,
            @AuthenticationPrincipal UserDetailsImpl user,
            HistorySearchConditionDto condition
    ) {
        log.info("search: user={}, HistorySearchConditionDto={}", user, condition);
        model.addAttribute("categories", historyService.getAllCategories());
        List<MealEntity> meals = historyService.getMealsBySearchCondition(user.getUsername(), condition);
        model.addAttribute("meals", meals);
        model.addAttribute("condition", condition);
        return "history";
    }
}