package net.peppinotech.service;

import net.peppinotech.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(User user) {
        String token = Jwts.builder().setSubject(String.valueOf(user.getId()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofHours(1))))
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256)).compact();
        String confirmationLink = "http://localhost:8080/confirm-email?token=" + token + "&username=" + user.getUsername();
        String emailBody = "Clicca sul seguente link per confermare la tua email: " + confirmationLink;
        user.setConfirmationToken(token);
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Confirmation email");
        email.setText(emailBody);

        javaMailSender.send(email);

    }
}
