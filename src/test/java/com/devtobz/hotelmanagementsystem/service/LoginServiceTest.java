package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.entity.LoginDetails;
import com.devtobz.hotelmanagementsystem.exception.EmployeeException;
import com.devtobz.hotelmanagementsystem.repository.EmployeeRepository;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.LoginServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    private Employee employee1;
    private LoginDetails loginDetails;
    @BeforeEach
    void setUp() {
        employee1 = Employee.builder().
                name("employeeTest1").
                age(20).
                address("Ondo").
                email("EmployeeTest1@gmail.com").
                role(Role.Manager).
                salary(30000).
                phoneNumber("08076433789").
                gender(Gender.Male).
                build();

        loginDetails = LoginDetails.builder().
                password("1234").
                username("Test1").
                build();
    }

    @Test
    void shouldAuthenticateEmployee() {
        //when
        String name = "employeeTest1";
       when(employeeRepository.findByName(name)).
               thenReturn(Optional.of(employee1));
        //verify
        String authenticateMessage = loginService.authenticate(loginDetails, name);
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(employeeArgumentCaptor.capture());
        Employee caputuredEmployee = employeeArgumentCaptor.getValue();
        //assert
        Assertions.assertThat(caputuredEmployee.getPassword()).isEqualTo("1234");
        Assertions.assertThat(authenticateMessage).isEqualTo("User credentials created successfully");
    }

    @Test
    void shouldNotFindEmployee(){
        String name = "employeeTest1";
        when(employeeRepository.findByName(name)).thenReturn(Optional.empty());


        Assertions.assertThatThrownBy(()-> loginService.authenticate(loginDetails,name)).
                isInstanceOf(EmployeeException.class).
                hasMessageContaining("Employee not found in the database");
    }
}