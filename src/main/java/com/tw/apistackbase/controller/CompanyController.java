package com.tw.apistackbase.controller;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public List<Company> getCompanyList() {
        return companyService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Company getCompanyById(@PathVariable("id") int id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> findCompaniesEmployee(@PathVariable("id") int id) {
        return companyService.findCompaniesEmployee(id);
    }
    

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> findCompanyPage(@RequestParam(name = "page") int page, @RequestParam(name = "pageSize") int pageSize) {
        return companyService.findAll(page - 1, pageSize);
    }


    @PostMapping
    public List<Company> insert(@RequestBody Company company) {
        companyService.insert(company);
        return companyService.findAll();
    }

    @PutMapping
    public Company update(@RequestBody Company company) {
        return companyService.update(company) ? company : null;
    }

    @DeleteMapping(value = "/{id}")
    public Company delete(@PathVariable int id) {
        Company company = companyService.getCompanyById(id);
        return companyService.delete(id) ? company : null;
    }
}
