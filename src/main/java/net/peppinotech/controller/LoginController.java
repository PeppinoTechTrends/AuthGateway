package net.peppinotech.controller;

import net.peppinotech.form.LoginForm;
import net.peppinotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String loginForm(@RequestParam(value = "loginError", required = false) String loginError,Model model) {
        if (loginError != null) {
            model.addAttribute("loginError", true);
        }
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }
}
