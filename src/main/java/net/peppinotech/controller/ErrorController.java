package net.peppinotech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
    @GetMapping
    public String showErrorPage(@RequestParam(required = false, name = "message") String message, Model model) {
        model.addAttribute("errorMessage", message);
        System.out.println("Error " + message);
        return "error";
    }

}
