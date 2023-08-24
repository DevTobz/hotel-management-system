package com.devtobz.hotelmanagementsystem.service;

import com.devtobz.hotelmanagementsystem.entity.dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.entity.Employee;
import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.entity.mapper.EmployeeMapper;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.entity.request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.repository.EmployeeRepository;
import com.devtobz.hotelmanagementsystem.service.serviceImpl.EmployeeServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee1;
    private Employee employee2;
    private EmployeeRequest request;
    private EmployeeUpdate updateRequest;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setup(){
         employee1 = Employee.builder().
                firstName("employeeTest1").
                age(20).
                lastName("Work").
                email("EmployeeTest1@gmail.com").
                role(Role.Manager).
                salary(30000).
                phoneNumber("08076433789").
                gender(Gender.Male).
                build();

        request = EmployeeRequest.builder().
                age(20).
                email("EmployeeTest1@gmail.com").
                firstName("employeeTest1").
                role(Role.Manager).
                salary(30000).
                phoneNumber("08076433789").
                gender(Gender.Male).
                build();


        employee2 = Employee.builder().
                firstName("employeeTest2").
                age(34).
                email("EmployeeTest2@gmail.com").
                role(Role.Receptionist).
                salary(30000).
                phoneNumber("08057657493").
                gender(Gender.Female).
                build();

        updateRequest = EmployeeUpdate.builder().
                age(22).
                email("EmployeeTest1@gmail.com").
                phoneNumber("08076433789").
                salary(50000).
                role(Role.Manager).build();

        employeeDto = EmployeeDto.builder().
                firstName(employee1.getFirstName()).
                age(employee1.getAge()).
                email(employee1.getEmail()).
                role(employee1.getRole()).
                salary(employee1.getSalary()).
                phoneNumber(employee1.getPhoneNumber()).
                build();

    }

    @Test
    void createEmployee() {

        //Mock
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        when(employeeMapper.apply(employee1)).thenReturn(employeeDto);

        // When
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.
                                                            forClass(Employee.class);
        employeeService.createEmployee(request);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee employeeCaptured = employeeArgumentCaptor.getValue();
        //Assert
        Assertions.assertThat(employeeCaptured).isEqualTo(employee1);
        //Assertions.assertThat(employeeCaptured.getName()).isEqualTo(request.getName());

    }

    @Test
    void shouldGetAllEmployee() {
        //Given
       List<Employee> employeeList= new ArrayList<>();

       employeeList.add(employee1);
        employeeList.add(employee2);
        //Mock
        when(employeeRepository.findAll()).thenReturn(employeeList);

        //When
        List<Employee> returnedEmployeeList = employeeService.getAllEmployee();

        //Verification of repository method called in service method
        verify(employeeRepository).findAll();

        //Assert
        Assertions.assertThat(returnedEmployeeList.size()).isEqualTo(employeeList.size());
        Assertions.assertThat(returnedEmployeeList).hasSize(2);
        Assertions.assertThat(returnedEmployeeList).contains(employee1);


    }

    @Test
    void editEmployee() {
        //Given


        //Mock

        //when(employeeRepository.findByName("employeeTest1")).thenReturn(Optional.ofNullable(employee1));
        //When
        employeeService.editEmployee("employeeTest1",updateRequest);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        //verify(employeeRepository).findByName("employeeTest1");
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee employeeCaptured = employeeArgumentCaptor.getValue();

        //Assert
        Assertions.assertThat(employeeCaptured.getRole()).isEqualTo(updateRequest.getRole());

    }

    @Test
    void shouldDeleteEmployee() {

        //Given


        //Mock
        /*when(employeeRepository.
                findByName("employeeTest1")).
                thenReturn(Optional.ofNullable(employee1));*/


        //When
        String employeeTestString = employeeService.deleteEmployee("employeeTest1");

        verify(employeeRepository).delete(employee1);
        //Assert
        /*Assertions.assertThat(employeeTestString).
                isEqualTo("Employee "+ employee1.getName() +
                        " have been deleted from the database");*/
    }
}