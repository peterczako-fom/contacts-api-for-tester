package hu.futureofmedia.task.contactsapi.companies.repositories;

import hu.futureofmedia.task.contactsapi.companies.entities.Company;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Long> {
    List<Company> findAll();

    Optional<Company> findById(Long id);
}
