package hu.futureofmedia.task.contactsapi.auth.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtTokenUtil {
    public boolean validate(String token) {
        return true;
    }

    public String generateAccessToken(Object principal) {
        return Jwts.builder()
                .setIssuer("Issuer")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60000L))
                .signWith(SignatureAlgorithm.HS256, "secret").compact();
    }

    public String getEmail(String token) {
        return "";
    }
}
