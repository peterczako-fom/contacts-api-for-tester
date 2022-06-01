package hu.futureofmedia.task.contactsapi.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.futureofmedia.task.contactsapi.auth.dtos.LoginDto;
import hu.futureofmedia.task.contactsapi.auth.dtos.TokenResponse;
import hu.futureofmedia.task.contactsapi.users.AppUserDetails;
import hu.futureofmedia.task.contactsapi.auth.services.TokenService;
import hu.futureofmedia.task.contactsapi.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenService refreshTokenService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginDto dto = null;
        try {
            dto = new ObjectMapper().readValue(request.getReader(), LoginDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dto == null) {
            throw new RuntimeException();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();

        String access_token = JwtUtil.generateAccessToken(userDetails);
        String refresh_token = JwtUtil.generateRefreshToken(userDetails);

        var refreshToken = refreshTokenService.saveRefreshToken(refresh_token);

        var tokens = new TokenResponse(access_token, refreshToken.getToken());
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
