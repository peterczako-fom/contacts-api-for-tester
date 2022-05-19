package hu.futureofmedia.task.contactsapi.contacts.repositories;

import hu.futureofmedia.task.contactsapi.contacts.entities.BannedDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannedDomainRepository extends JpaRepository<BannedDomain, Long> {

    Optional<BannedDomain> findByName(String domainName);
}
