package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Employee;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.Repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee() {
    }

    @Test
    void shouldGetAllEmployee() {
        //Given
       List<Employee> employeeList= new ArrayList<>();

        Employee employee1 = Employee.builder().
                age(20).
                address("Ondo").
                email("EmployeeTest1@gmail.com").
                role(Role.Manager).
                salary(30000).
                phoneNumber("08076433789").
                gender(Gender.Male).
                build();

        Employee employee2 = Employee.builder().
                age(34).
                address("Abuja").
                email("EmployeeTest2@gmail.com").
                role(Role.Receptionist).
                salary(30000).
                phoneNumber("08057657493").
                gender(Gender.Female).
                build();

        employeeList.add(employee1);
        employeeList.add(employee2);
        //Mock
        when(employeeRepository.findAll()).thenReturn(employeeList);

        //When
        List<Employee> returnedEmployeeList = employeeService.getAllEmployee();

        //Verification of repository method called in service method
        verify(employeeRepository).findAll();

        //Assert
        Assertions.assertThat(returnedEmployeeList).containsAll(employeeList);
        Assertions.assertThat(returnedEmployeeList).doesNotContainNull();
        Assertions.assertThat(returnedEmployeeList).hasSize(2);


    }

    @Test
    void editEmployee() {
    }

    @Test
    void shouldDeleteEmployee() {

        //Given
        Employee employeeTest = Employee.builder().
                age(34).
                name("EmployeeTest")
                .address("Abuja").
                email("EmployeeTest2@gmail.com").
                role(Role.Receptionist).
                salary(30000).
                phoneNumber("08057657493").
                gender(Gender.Female).
                build();


        //Mock
        when(employeeRepository.
                findByName("EmployeeTest")).
                thenReturn(Optional.ofNullable(employeeTest));


        //When
        String employeeTestString = employeeService.deleteEmployee("EmployeeTest");

        //Assert
        Assertions.assertThat(employeeTestString).
                isEqualTo("Employee "+ employeeTest.getName() +
                        " have been deleted from the database");
    }
}