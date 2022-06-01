package hu.futureofmedia.task.contactsapi.auth.services;

import hu.futureofmedia.task.contactsapi.users.entities.RefreshToken;

public interface TokenService {

    RefreshToken saveRefreshToken(String token);

    RefreshToken getRefreshTokenById(Long id);

}
