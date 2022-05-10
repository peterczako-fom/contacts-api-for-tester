package hu.futureofmedia.task.contactsapi.service;

import hu.futureofmedia.task.contactsapi.entities.Company;

import java.util.List;

public interface CompanyService {

    Company findCompanyById(Long id);

    List<Company> findAll();
}
