package com.tw.apistackbase.dao;

import com.tw.apistackbase.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    Employee getEmployeeById(int id);
    List<Employee> findAll();
    List<Employee> findEmployeeByGender(String gender);
    boolean insert(Employee employee);
    boolean update(Employee employee);
    boolean delete(int id);
    List<Employee> findAll(int page, int pageSize);
}
