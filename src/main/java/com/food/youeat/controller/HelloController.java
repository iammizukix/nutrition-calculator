package com.food.youeat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
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