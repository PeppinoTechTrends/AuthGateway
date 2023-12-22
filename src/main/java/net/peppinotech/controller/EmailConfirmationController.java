package net.peppinotech.controller;

import net.peppinotech.User;
import net.peppinotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/confirm-email")
public class EmailConfirmationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String confirmEmail(@RequestParam("token") String token, @RequestParam("username") String username, Model model) {
        User user = userRepository.findByUsername(username);
        if (user != null && token.equals(user.getConfirmationToken())) {
            user.setEmailConfirmed(true);
            userRepository.save(user);
            model.addAttribute("emailConfirmed", true);
            return "redirect:/login";
        } else {
            System.out.println("Token inValid");
            model.addAttribute("loginError", true);
            return "redirect:/error";
        }
    }

}
