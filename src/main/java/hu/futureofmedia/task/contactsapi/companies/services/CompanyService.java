package hu.futureofmedia.task.contactsapi.companies.services;

import hu.futureofmedia.task.contactsapi.companies.entities.Company;

import java.util.List;

public interface CompanyService {

    Company findCompanyById(Long id);

    List<Company> findAll();
}
