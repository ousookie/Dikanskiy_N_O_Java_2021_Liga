package com.example.liquibasedemo.services;

import com.example.liquibasedemo.dto.CustomerDto;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> enumerateCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer c : customers) {
            customerDtoList.add(new CustomerDto(c.getId(), c.getName()));
        }
        return customerDtoList;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public CustomerDto getCustomerById(String id) {
        Customer customer = null;
        if (customerRepository.findById(UUID.fromString(id)).isPresent()) {
            customer = customerRepository
                    .findById(
                            UUID.fromString(id)
                    )
                    .get();
        }
        return new CustomerDto(customer.getId(), customer.getName());
    }

    public void deleteCustomerById(String id) {
        if (customerRepository.existsById(UUID.fromString(id))) {
            customerRepository.deleteById(UUID.fromString(id));
        }
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer currentCustomer = null;
        if (customerRepository.existsById(UUID.fromString(id))) {
            currentCustomer = customerRepository.getById(UUID.fromString(id));
            currentCustomer.setId(customer.getId());
            currentCustomer.setName(customer.getName());
            currentCustomer.setRating(customer.getRating());
        }
        return saveCustomer(currentCustomer);
    }

}
