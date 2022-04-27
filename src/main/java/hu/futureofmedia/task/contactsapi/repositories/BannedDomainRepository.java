package hu.futureofmedia.task.contactsapi.repositories;

import hu.futureofmedia.task.contactsapi.entities.BannedDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannedDomainRepository extends JpaRepository<BannedDomain, Long> {

    Optional<BannedDomain> findByName(String domainName);
}
