package net.peppinotech.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping(value = "testAdmin")

    @PostAuthorize("hasRole('ADMIN')")
    public String homeAdmin() {
        return "admin";
    }

}
