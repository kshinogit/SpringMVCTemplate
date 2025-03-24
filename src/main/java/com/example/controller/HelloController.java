package com.example.controller;

import com.example.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @Autowired
    private AppProperties appProperties;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Thymeleafを使ったSpring MVCプロジェクト");
        model.addAttribute("appName", appProperties.getAppName());
        model.addAttribute("appVersion", appProperties.getAppVersion());
        return "hello";
    }
}
