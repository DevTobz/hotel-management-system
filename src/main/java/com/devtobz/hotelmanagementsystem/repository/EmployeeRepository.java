package com.devtobz.hotelmanagementsystem.repository;

import com.devtobz.hotelmanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {



    Optional<Employee> findByUsername(String username);

    Optional<Employee> findByEmail(String email);
}
