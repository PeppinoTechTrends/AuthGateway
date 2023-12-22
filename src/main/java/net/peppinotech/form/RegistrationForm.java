package net.peppinotech.form;

import net.peppinotech.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullname, email, city, state, zip, phoneNumber);
    }
}
