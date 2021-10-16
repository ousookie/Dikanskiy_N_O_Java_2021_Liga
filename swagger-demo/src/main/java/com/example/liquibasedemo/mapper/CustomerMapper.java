package com.example.liquibasedemo.mapper;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDTO toCustomerDto(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName());
    }

}
