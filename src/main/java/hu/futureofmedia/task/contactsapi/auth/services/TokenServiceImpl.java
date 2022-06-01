package hu.futureofmedia.task.contactsapi.auth.services;

import hu.futureofmedia.task.contactsapi.auth.repositories.RefreshTokenRepository;
import hu.futureofmedia.task.contactsapi.users.entities.RefreshToken;
import hu.futureofmedia.task.contactsapi.users.services.UserServiceInner;
import hu.futureofmedia.task.contactsapi.utility.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserServiceInner userServiceInner;

    @Override
    public RefreshToken saveRefreshToken(String token) {
        String email = JwtUtil.getEmailFromAccessToken(token);
        var user = userServiceInner.findUserByEmailOrThrow(email);

        List<RefreshToken> activeRefreshTokens = refreshTokenRepository.findAllActiveTokenByEmail(email);
        activeRefreshTokens.forEach(refreshToken -> refreshToken.setActive(false));
        refreshTokenRepository.saveAll(activeRefreshTokens);

        var refreshToken = new RefreshToken(null, token, true, user);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken getRefreshTokenById(Long id) {
        return null;
    }

}
