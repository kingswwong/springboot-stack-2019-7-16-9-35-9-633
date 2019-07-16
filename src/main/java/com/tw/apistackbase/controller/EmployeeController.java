package com.tw.apistackbase.controller;

//import com.github.pagehelper.PageInfo;

import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<Employee> getEmployeeList() {
        return employeeService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = "gender")
    public List<Employee> findEmployeeByGender(@RequestParam(name = "gender") String gender) {
        return employeeService.findEmployeeByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> findEmployeePage(@RequestParam(name = "page") int page, @RequestParam(name = "pageSize") int pageSize) {
        return employeeService.findAll(page - 1, pageSize);
    }


    @PostMapping
    public List<Employee> insert(@RequestBody Employee employee) {
        employeeService.insert(employee);
        return employeeService.findAll();
    }

    @PutMapping
    public Employee update(@RequestBody Employee employee) {
        return employeeService.update(employee) ? employee : null;
    }

    @DeleteMapping(value = "/{id}")
    public Employee delete(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employeeService.delete(id) ? employee : null;
    }

}
