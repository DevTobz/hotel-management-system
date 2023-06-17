package com.devtobz.hotelmanagementsystem.controller;

import com.devtobz.hotelmanagementsystem.entity.dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.service.EmployeeService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    private EmployeeRequest request;
    private Employee employee;

    private EmployeeDto employeeDto;
    private List<Employee> employeeList = new ArrayList<>();

    @BeforeEach
    void setUp() {
         request = EmployeeRequest.builder().
                address("Ondo").
                age(20).
                email("EmployeeTest1@gmail.com").
                name("employeeTest1").
                role(Role.Manager).
                salary(30000).
                phoneNumber("08076433789").
                gender(Gender.Male).
                build();
         employee = Employee.builder().
                name(request.getName()).
                gender(request.getGender()).
                age(request.getAge()).
                salary(request.getSalary()).
                phoneNumber(request.getPhoneNumber()).
                email(request.getEmail()).
                age(request.getAge()).
                address(request.getAddress()).build();

       employeeDto = new EmployeeDto(employee.getName(),
                employee.getAge(),
                employee.getGender(),
                employee.getRole(),
                employee.getSalary(),
                employee.getPhoneNumber(),
                employee.getAddress(),
                employee.getEmail());

       employeeList.add(employee);

    }

    @Test
    void employeeController_shouldCreateEmployee() throws Exception {

       when(employeeService.createEmployee(request)).thenReturn(employeeDto);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.
                post("/homepage/employee/createEmployee").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(request)));
       response.andDo(MockMvcResultHandlers.print());
       response.andExpect(status().isCreated());
       response.andExpect(jsonPath("$.name", CoreMatchers.is(employeeDto.name())));

    }

    @Test
    void getAllEmployee() throws Exception {

        when(employeeService.getAllEmployee()).thenReturn(employeeList);
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.
                get("/homepage/employee/getAllEmployee").
                contentType(MediaType.APPLICATION_JSON));
        actions.andDo(print());
       actions.andExpect(jsonPath("$[0].name",CoreMatchers.is("employeeTest1")));
    }

    @Test
    void editEmployee() {
    }

    @Test
    void deleteEmployee() {
        when(employeeService.deleteEmployee(request.getName())).thenReturn("Deleted");
    }
}