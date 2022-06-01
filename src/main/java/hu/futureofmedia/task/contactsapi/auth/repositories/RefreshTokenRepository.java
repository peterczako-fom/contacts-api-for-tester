package hu.futureofmedia.task.contactsapi.auth.repositories;

import hu.futureofmedia.task.contactsapi.users.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Query("select e from RefreshToken e where e.user.email = :email")
    List<RefreshToken> findAllActiveTokenByEmail(String email);

}
