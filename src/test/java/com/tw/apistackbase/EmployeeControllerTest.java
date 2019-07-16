package com.tw.apistackbase;

import com.tw.apistackbase.controller.EmployeeController;
import com.tw.apistackbase.entity.Employee;
import com.tw.apistackbase.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void should_find_employee_by_id() throws Exception {
        Employee employee = new Employee(1,"小明",21,"男",3000);
        when(employeeService.getEmployeeById(anyInt())).thenReturn(employee);
        ResultActions resultActions = mockMvc.perform(get("/employees/{id}",employee.getId()));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("小明")))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.age",is(21)))
                .andExpect(jsonPath("$.gender",is("男")))
                .andExpect(jsonPath("$.salary",is(3000)));
    }

    @Test
    void should_find_all_employee() throws Exception {

        Employee employee1 = new Employee(1,"小明1",21,"男",3000);
        Employee employee2 = new Employee(2,"小明2",22,"男",4000);
        Employee employee3 = new Employee(3,"小明3",23,"男",5000);
        Employee employee4 = new Employee(4,"小明4",24,"男",6000);
        List<Employee> employeeList = Arrays.asList(employee1,employee2,employee3,employee4);

        when(employeeService.findAll()).thenReturn(employeeList);
        ResultActions resultActions = mockMvc.perform(get("/employees"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name",is("小明1")))
                .andExpect(jsonPath("$.[1].name",is("小明2")))
                .andExpect(jsonPath("$.[2].name",is("小明3")))
                .andExpect(jsonPath("$.[3].name",is("小明4")));
    }

    @Test
    void should_find_employee_by_gender() throws Exception {

        Employee employee1 = new Employee(1,"小明1",21,"男",3000);
        Employee employee2 = new Employee(2,"小明2",21,"男",400);
        List<Employee> employeeList = Arrays.asList(employee1,employee2);

        when(employeeService.findEmployeeByGender(anyString())).thenReturn(employeeList);
        ResultActions resultActions = mockMvc.perform(get("/employees?gender={gender}","男"));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name",is("小明1")))
                .andExpect(jsonPath("$.[1].name",is("小明2")));
    }

    @Test
    void should_find_employee_by_page_and_page_size() throws Exception {

        Employee employee1 = new Employee(1,"小明1",21,"男",3000);
        Employee employee2 = new Employee(2,"小明2",22,"男",4000);
        Employee employee3 = new Employee(3,"小明3",23,"男",5000);
        List<Employee> employeeList = Arrays.asList(employee1,employee2,employee3);

        when(employeeService.findAll(anyInt(),anyInt())).thenReturn(employeeList.subList(0,2));
        ResultActions resultActions = mockMvc.perform(get("/employees?page={page}&pageSize={pageSize}",1,2));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name",is("小明1")))
                .andExpect(jsonPath("$.[1].name",is("小明2")));
        when(employeeService.findAll(anyInt(),anyInt())).thenReturn(employeeList.subList(2,3));
        resultActions = mockMvc.perform(get("/employees?page={page}&pageSize={pageSize}",2,2));
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name",is("小明3")));
    }


}