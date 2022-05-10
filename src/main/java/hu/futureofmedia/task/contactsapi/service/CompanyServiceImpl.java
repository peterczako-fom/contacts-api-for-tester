package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.entities.Company;
import hu.futureofmedia.task.contactsapi.exception.IdNotFoundException;
import hu.futureofmedia.task.contactsapi.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public Company findCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id, Company.class));
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
}
