package com.syseva.syseva.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {

        return "index"; // Correspond à src/main/resources/templates/index.html
    }


}
