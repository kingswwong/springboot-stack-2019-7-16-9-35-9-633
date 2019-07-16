package com.tw.apistackbase.service;

import com.tw.apistackbase.dao.CompanyRepository;
import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public Company getCompanyById(int id) {
        return companyRepository.getCompanyById(id);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findAll(int page,int pageSize) {
        return companyRepository.findAll(page,pageSize);
    }

    public boolean insert(Company company) {
        return companyRepository.insert(company);
    }

    public boolean update(Company company) {
        return companyRepository.update(company);
    }

    public boolean delete(int id) {
        return companyRepository.delete(id);
    }

    public List<Employee> findCompaniesEmployee(int id){
        return companyRepository.findCompaniesEmployee(id);
    }
}
