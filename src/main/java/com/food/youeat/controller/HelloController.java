package com.food.youeat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello success");
        return "hello";
    }

    @GetMapping("/general")
    public String general() {
        return "general";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

}