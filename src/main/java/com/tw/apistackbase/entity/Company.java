package com.tw.apistackbase.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
    private int id;
    private String companyName;
    private List<Employee> employeeList = new ArrayList<>();
    private int employeesNumber;

    public Company() {
    }

    public Company(int id, String companyName, List<Employee> employeeList) {
        this.id = id;
        this.companyName = companyName;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getEmployeesNumber() {
        return employeeList.size();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void update(Company company) {
        this.companyName = company.companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
