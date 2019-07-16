package com.tw.apistackbase;

import com.tw.apistackbase.controller.CompanyController;
import com.tw.apistackbase.entity.Company;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Test
    void should_find_company_by_id() throws Exception {
        List<Employee> employeeListInCompanyOne = new ArrayList<>();
        employeeListInCompanyOne.add(new Employee(0, "Xiaoming1", 20, "男", 6000));
        employeeListInCompanyOne.add(new Employee(1, "Xiaohong1", 19, "女", 7000));
        employeeListInCompanyOne.add(new Employee(2, "Xiaozhi1", 15, "男", 8000));
        Company company1 = new Company(1,"company1",employeeListInCompanyOne);
        when(companyService.getCompanyById(anyInt())).thenReturn(company1);
        ResultActions resultActions = mockMvc.perform(get("/companies/{id}",1));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.companyName",is("company1")))
                .andExpect(jsonPath("$.employeesNumber",is(3)));
    }

    @Test
    void should_find_all_company() throws Exception {

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
        List<Company> companyList = Arrays.asList(company1,company2);

        when(companyService.findAll()).thenReturn(companyList);
        ResultActions resultActions = mockMvc.perform(get("/companies"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].companyName",is("company1")))
                .andExpect(jsonPath("$.[1].companyName",is("company2")));
    }

    @Test
    void should_find_companies_by_page_and_page_size() throws Exception {

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
        List<Company> companyList = Arrays.asList(company1,company2);

        when(companyService.findAll(anyInt(),anyInt())).thenReturn(companyList.subList(0,1));
        ResultActions resultActions = mockMvc.perform(get("/companies?page={page}&pageSize={pageSize}",1,1));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].companyName",is("company1")));
    }

    @Test
    void should_find_employee_in_company_by_company_id() throws Exception {

        List<Employee> employeeListInCompanyOne = new ArrayList<>();
        employeeListInCompanyOne.add(new Employee(0, "Xiaoming1", 20, "男", 6000));
        employeeListInCompanyOne.add(new Employee(1, "Xiaohong1", 19, "女", 7000));
        employeeListInCompanyOne.add(new Employee(2, "Xiaozhi1", 15, "男", 8000));

        when(companyService.findCompaniesEmployee(anyInt())).thenReturn(employeeListInCompanyOne);
        ResultActions resultActions = mockMvc.perform(get("/companies/{id}/employees",1));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id",is(0)))
                .andExpect(jsonPath("$.[1].id",is(1)))
                .andExpect(jsonPath("$.[2].id",is(2)));
    }
}
