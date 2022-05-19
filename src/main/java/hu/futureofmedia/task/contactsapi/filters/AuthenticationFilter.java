package hu.futureofmedia.task.contactsapi.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.futureofmedia.task.contactsapi.auth.dtos.TokenResponse;
import hu.futureofmedia.task.contactsapi.users.AppUserDetails;
import hu.futureofmedia.task.contactsapi.utility.JwtUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        String access_token = JwtUtil.generateAccessToken(userDetails);
        String refresh_token = JwtUtil.generateRefreshToken(userDetails);

        var tokens = new TokenResponse(access_token, refresh_token);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
