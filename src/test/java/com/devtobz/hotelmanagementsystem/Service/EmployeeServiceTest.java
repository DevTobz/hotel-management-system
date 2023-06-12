package com.devtobz.hotelmanagementsystem.Service;

import com.devtobz.hotelmanagementsystem.Entity.Dto.EmployeeDto;
import com.devtobz.hotelmanagementsystem.Entity.Employee;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.Entity.Enum.Role;
import com.devtobz.hotelmanagementsystem.Entity.Mapper.EmployeeMapper;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeRequest;
import com.devtobz.hotelmanagementsystem.Entity.Request.EmployeeUpdate;
import com.devtobz.hotelmanagementsystem.Repository.EmployeeRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;
    private EmployeeRequest request;
    private EmployeeUpdate updateRequest;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setup(){
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


        employee2 = Employee.builder().
                name("employeeTest2").
                age(34).
                address("Abuja").
                email("EmployeeTest2@gmail.com").
                role(Role.Receptionist).
                salary(30000).
                phoneNumber("08057657493").
                gender(Gender.Female).
                build();

        updateRequest = EmployeeUpdate.builder().
                age(22).
                address("Ondo").
                email("EmployeeTest1@gmail.com").
                phoneNumber("08076433789").
                salary(50000).
                role(Role.Manager).build();

        employeeDto = EmployeeDto.builder().
                name(employee1.getName()).
                address(employee1.getAddress()).
                age(employee1.getAge()).
                address(employee1.getAddress()).
                email(employee1.getEmail()).
                role(employee1.getRole()).
                salary(employee1.getSalary()).
                phoneNumber(employee1.getPhoneNumber()).
                build();

    }

    @Test
    void createEmployee() {

        // When
        when(employeeRepository.save(employee1)).thenReturn(employee1);
        when(employeeMapper.apply(employee1)).thenReturn(employeeDto);
        //Mock
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.
                                                            forClass(Employee.class);
        employeeService.createEmployee(request);

        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee employeeCaptured = employeeArgumentCaptor.getValue();
        //Assert
        Assertions.assertThat(employeeCaptured).isEqualTo(employee1);
        Assertions.assertThat(employeeCaptured.getName()).isEqualTo(request.getName());

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
        //Assertions.assertThat(returnedEmployeeList).asList().contains(employeeDto);
        Assertions.assertThat(returnedEmployeeList).hasSize(2);
        Assertions.assertThat(returnedEmployeeList).contains(employee1);


    }

    @Test
    void editEmployee() {
        //Given


        //Mock

        when(employeeRepository.findByName("employeeTest1")).thenReturn(Optional.ofNullable(employee1));
        //When
        employeeService.editEmployee("employeeTest1",updateRequest);

        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);

        verify(employeeRepository).findByName("employeeTest1");
        verify(employeeRepository).save(employeeArgumentCaptor.capture());

        Employee employeeCaptured = employeeArgumentCaptor.getValue();

        //Assert
        Assertions.assertThat(employeeCaptured.getRole()).isEqualTo(updateRequest.getRole());

    }

    @Test
    void shouldDeleteEmployee() {

        //Given


        //Mock
        when(employeeRepository.
                findByName("employeeTest1")).
                thenReturn(Optional.ofNullable(employee1));


        //When
        String employeeTestString = employeeService.deleteEmployee("employeeTest1");

        verify(employeeRepository).delete(employee1);
        //Assert
        Assertions.assertThat(employeeTestString).
                isEqualTo("Employee "+ employee1.getName() +
                        " have been deleted from the database");
    }
}