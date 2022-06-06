package springmvc.services;

import org.springframework.stereotype.Service;
import springmvc.models.Company;
import springmvc.repositories.CompanyRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void saveCompany(Company company) {
        companyRepository.saveCompany(company);
    }


    public Company findCompanyById(UUID companyId) {
        return companyRepository.findCompanyById(companyId);
    }


    public List<Company> findAllCompanies() {
        return companyRepository.findAllCompanies();
    }

    public void removeCompanyById(UUID companyId) {
        companyRepository.removeCompanyById(companyId);
    }

    public void updateCompany(UUID companyId, Company company) {
        companyRepository.updateCompany(companyId, company);
    }
}
