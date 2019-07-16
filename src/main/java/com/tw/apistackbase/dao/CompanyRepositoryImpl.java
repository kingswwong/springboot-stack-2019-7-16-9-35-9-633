package com.tw.apistackbase.dao;

import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepositoryImpl implements CompanyRepository {

    private List<Company> companyList = new ArrayList<>();

    public CompanyRepositoryImpl() {

        List<Employee> employeeListInCompanyOne = new ArrayList<>();
        employeeListInCompanyOne.add(new Employee(0, "Xiaoming1", 20, "男", 6000));
        employeeListInCompanyOne.add(new Employee(1, "Xiaohong1", 19, "女", 7000));
        employeeListInCompanyOne.add(new Employee(2, "Xiaozhi1", 15, "男", 8000));
        List<Employee> employeeListInCompanyTwo = new ArrayList<>();
        employeeListInCompanyTwo.add(new Employee(0, "Xiaoming2", 20, "男", 3000));
        employeeListInCompanyTwo.add(new Employee(1, "Xiaoming2", 19, "女", 4000));
        employeeListInCompanyTwo.add(new Employee(2, "Xiaozhi2", 15, "男", 5000));
        Company company1 = new Company(1,"company1",employeeListInCompanyOne);
        Company company2 = new Company(2,"company2",employeeListInCompanyTwo);
        companyList.add(company1);
        companyList.add(company2);
    }

    @Override
    public Company getCompanyById(int id) {
        List<Company> companies =  companyList.stream().filter(company -> id == company.getId()).collect(Collectors.toList());

        return companies.size() > 0 ? companies.get(0) : null;
    }

    @Override
    public List<Company> findAll() {
        return companyList;
    }

    @Override
    public boolean insert(Company company) {
        if(companyList.contains(company)){
            return false;
        }
        companyList.add(company);
        return true;
    }

    @Override
    public boolean update(Company company) {
        Company exactCompany= getCompanyById(company.getId());
        if(exactCompany == null){
            return false;
        }
        exactCompany.setCompanyName(company.getCompanyName());
        return true;
    }

    @Override
    public boolean delete(int id) {
        Company exactCompany= getCompanyById(id);
        if(exactCompany == null){
            return false;
        }
        exactCompany.getEmployeeList().removeAll(exactCompany.getEmployeeList());
        return true;
    }

    @Override
    public List<Company> findAll(int page, int pageSize) {
        int startIndex = page * pageSize;
        int endIndex = Math.min(companyList.size(),page * pageSize + pageSize);
        return companyList.subList(startIndex, endIndex);
    }

    @Override
    public List<Employee> findCompaniesEmployee(int id) {
        return getCompanyById(id) != null ?getCompanyById(id).getEmployeeList() : null;
    }
}
