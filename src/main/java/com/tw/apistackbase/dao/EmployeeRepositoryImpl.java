package com.tw.apistackbase.dao;

import com.tw.apistackbase.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    public List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepositoryImpl(){
        employeeList.add(new Employee(0,"Xiaoming",20,"男",6000));
        employeeList.add(new Employee(1,"Xiaoming",19,"女",7000));
        employeeList.add(new Employee(2,"Xiaozhi",15,"男",8000));
    }

    @Override
    public Employee getEmployeeById(int id) {
        List<Employee> employees =  employeeList.stream().filter(employee1 -> id == employee1.getId()).collect(Collectors.toList());

        return employees.size() > 0 ? employees.get(0) : null;
    }

    @Override
    public List<Employee> findAll() {
        return employeeList;
    }

    @Override
    public boolean insert(Employee employee) {
        if(employeeList.contains(employee)){
            return false;
        }
        employeeList.add(employee);
        return true;
    }

    @Override
    public boolean update(Employee employee) {
        Employee exactEmployee= getEmployeeById(employee.getId());
        if(exactEmployee == null){
            return false;
        }
        exactEmployee.update(employee);
        return true;
    }

    @Override
    public boolean delete(int id) {
        Employee exactEmployee= getEmployeeById(id);
        if(exactEmployee == null){
            return false;
        }
        employeeList.remove(exactEmployee);
        return true;
    }

    @Override
    public List<Employee> findEmployeeByGender(String gender) {
        return employeeList.stream().filter(employee1 -> employee1.getGender().equals(gender)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAll(int page, int pageSize) {
        int startIndex = page * pageSize;
        int endIndex = Math.min(employeeList.size(),page * pageSize + pageSize);
        return employeeList.subList(startIndex, endIndex);
    }
}
