package com.devtobz.hotelmanagementsystem.Repository;

import com.devtobz.hotelmanagementsystem.entity.Customer;
import com.devtobz.hotelmanagementsystem.entity.Enum.CheckOutStatus;
import com.devtobz.hotelmanagementsystem.entity.Enum.Gender;
import com.devtobz.hotelmanagementsystem.entity.Enum.IdentificationType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldFindCustomerByName() {
        //given
        Customer customer = new Customer();
        customer.setName("Akins");
        customer.setGender(Gender.Male);
        customer.setCheckOutStatus(CheckOutStatus.Checked_Out);
        customer.setCountry("Nigeria");
        customer.setDeposit(9000);
        customer.setIdentificationType(IdentificationType.IDCard);
        customer.setPhoneNumber("08097563328");
        customerRepository.save(customer);

        //when
      Customer customerFound = customerRepository.findByName("Akins").get();

        //then

        Assertions.assertThat(customer).isEqualTo(customerFound);
    }
}