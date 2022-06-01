package hu.futureofmedia.task.contactsapi.utility;

import hu.futureofmedia.task.contactsapi.users.AppUserDetails;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtUtil {

    private static final String ISSUER = "issuer";
    private static final String SECRET = "secret";

    public static String generateAccessToken(AppUserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .setIssuer(ISSUER)
                .addClaims(Map.of(
                        "roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String generateAccessToken(UserDto userDto) {
        return Jwts.builder()
                .setSubject(userDto.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .setIssuer(ISSUER)
                .addClaims(Map.of(
                        "roles", List.of()))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static String generateRefreshToken(AppUserDetails userDetails) {
       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Claims decode(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token).getBody();
    }

    public static String getEmailFromAccessToken(String token) {
        return decode(token).getSubject();
    }


    public static boolean tokenExpired(String token) {
        return decode(token).getExpiration()
                .before(new Date(System.currentTimeMillis()));
    }
}
