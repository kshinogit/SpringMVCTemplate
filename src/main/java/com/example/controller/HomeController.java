package com.example.controller;

import com.example.config.AppProperties;
import com.example.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private AppProperties appProperties;

    @GetMapping("/")
    public String home(Model model) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
        log.info("[start] HomeController");
        model.addAttribute("pageId", "home");
        return "home";
    }
}
