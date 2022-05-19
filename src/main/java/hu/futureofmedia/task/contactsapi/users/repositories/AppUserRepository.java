package hu.futureofmedia.task.contactsapi.users.repositories;

import hu.futureofmedia.task.contactsapi.users.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
