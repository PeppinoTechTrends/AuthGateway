package net.peppinotech.service;

import net.peppinotech.User;
import net.peppinotech.form.RegistrationForm;
import net.peppinotech.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegistrationService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private TokenService tokenService;
    
    public boolean successRegistration(RegistrationForm form, Model model){
        if (userRepository.findByUsername(form.getUsername()) != null) {
            model.addAttribute("usernameExists", true);
            return false;
        }

        if (userRepository.findByEmail(form.getEmail()) != null) {
            model.addAttribute("emailExists", true);
            return false;
        }
        return true;
    }
}
