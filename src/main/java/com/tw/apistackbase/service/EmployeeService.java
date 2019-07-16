package com.tw.apistackbase.service;

import com.tw.apistackbase.dao.EmployeeRepository;
import com.tw.apistackbase.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeById(id);
    }


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public List<Employee> findAll(int page, int pageSize) {
        return employeeRepository.findAll(page,pageSize);
    }

    public List<Employee> findEmployeeByGender(String gender) {
        return employeeRepository.findEmployeeByGender(gender);
    }

    public boolean insert(Employee employee) {
        return employeeRepository.insert(employee);
    }

    public boolean update(Employee employee) {
       return employeeRepository.update(employee);
    }

    public boolean delete(int id) {
        return employeeRepository.delete(id);
    }

}
