package com.tw.apistackbase.dao;


import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;

import java.util.List;

public interface CompanyRepository {
    Company getCompanyById(int id);
    List<Company> findAll();
    boolean insert(Company company);
    boolean update(Company company);
    boolean delete(int id);
    List<Company> findAll(int page, int pageSize);
    List<Employee> findCompaniesEmployee(int id);

}
