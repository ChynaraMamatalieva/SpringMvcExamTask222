package springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springmvc.models.Company;
import springmvc.models.Group;
import springmvc.services.CompanyService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @ModelAttribute("companyList")
    public List<Company>findAllCompanies(){
        return companyService.findAllCompanies();
    }
    @GetMapping
    public String findAll(){
        return "companies/allCompanies";
    }
    @GetMapping("/save")
    public String saveCompany(Model model){
        model.addAttribute("emptyCompany", new Company());
        return "companies/saveNewCompany";
    }
    @PostMapping("/save")
    public String saveCompany(Company company){
        companyService.saveCompany(company);
        return "redirect:/api/companies";
    }

    @GetMapping("/update/{companyId}")
    public String updateCompany(Model model, @PathVariable ("companyId")UUID companyId){

        model.addAttribute("updateCompany", companyService.findCompanyById(companyId));
        return "companies/updateCompany";
    }
    @PostMapping ("/update/{companyId}")
    public String updateCompany(Company company, @PathVariable ("companyId")UUID companyId){
        companyService.updateCompany(companyId, company);
        return "redirect:/api/companies";
    }


    @GetMapping ("/delete/{companyId}")
    public String delete(@PathVariable UUID companyId){
        companyService.removeCompanyById(companyId);
        return "redirect:/api/companies";
    }

}
