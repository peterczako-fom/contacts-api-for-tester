package hu.futureofmedia.task.contactsapi.auth.controllers;

import hu.futureofmedia.task.contactsapi.auth.dtos.TokenResponse;
import hu.futureofmedia.task.contactsapi.users.dtos.UserDto;
import hu.futureofmedia.task.contactsapi.users.services.UserService;
import hu.futureofmedia.task.contactsapi.utility.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader
    ) {
        if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("missing refresh token");
        }
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        Claims claims = JwtUtil.decode(refreshToken);

        String username = claims.getSubject();
        UserDto userDto = userService.getUserByEmail(username);

        String accessToken = JwtUtil.generateAccessToken(userDto);

        return new TokenResponse(accessToken, refreshToken);
    }
}
