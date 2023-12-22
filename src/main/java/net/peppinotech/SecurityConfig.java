package net.peppinotech;

import net.peppinotech.repository.UserRepository;
import net.peppinotech.service.UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserRepository userRepository) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) -> {
                    authorizeHttpRequests.requestMatchers("/register", "/login", "/error", "/confirm-email/**").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN").anyRequest().authenticated();
                }).formLogin(formLogin -> formLogin.loginPage("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?loginError=true"))
                .userDetailsService(userDetailsService(userRepository));

        return http.build();
    }


    private UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            net.peppinotech.User userFromDb = userRepo.findByUsername(username);
            if (userFromDb != null) {
                return new User(userFromDb.getUsername(), userFromDb.getPassword(), List.of(new SimpleGrantedAuthority(userFromDb.getRole())));
            }
            throw new UsernameNotFoundException("User not found");
        };
    }
}
