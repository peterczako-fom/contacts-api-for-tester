package hu.futureofmedia.task.contactsapi.utility;

import hu.futureofmedia.task.contactsapi.users.AppUserDetails;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String issuer = "issuer";

    public static String generateAccessToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .setIssuer(issuer)
                .addClaims(Map.of(
                        "roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    public static String generateAccessToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .setIssuer(issuer)
                .addClaims(Map.of(
                        "roles", List.of()))
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    public static String generateRefreshToken(AppUserDetails userDetails) {
       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .setIssuer(issuer)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }

    public static Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token).getBody();
    }


}
