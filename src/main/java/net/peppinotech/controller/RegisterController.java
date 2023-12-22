package net.peppinotech.controller;

import net.peppinotech.User;
import net.peppinotech.form.RegistrationForm;
import net.peppinotech.repository.UserRepository;
import net.peppinotech.service.RegistrationService;
import net.peppinotech.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public String processRegistration(Model model, RegistrationForm form) {
        if(registrationService.successRegistration(form,model)){
            User user = form.toUser(passwordEncoder);
            user.setRole(user.getUsername().equals("peppone") ? "ROLE_ADMIN" : "ROLE_USER");
            tokenService.sendConfirmationEmail(user);
            return "redirect:/login";
        }else {
            return "registration";
        }
    }

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

}
