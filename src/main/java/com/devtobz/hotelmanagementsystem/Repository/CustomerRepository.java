package com.devtobz.hotelmanagementsystem.Repository;

import com.devtobz.hotelmanagementsystem.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByName(String name);
}
