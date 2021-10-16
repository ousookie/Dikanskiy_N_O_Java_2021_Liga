package com.example.liquibasedemo.service;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.exception.UserNotFoundException;
import com.example.liquibasedemo.mapper.CustomerMapper;
import com.example.liquibasedemo.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<CustomerDTO> enumerateCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public CustomerDTO getCustomerById(String id) {
        Customer customer;
        if (customerRepository.findById(UUID.fromString(id)).isPresent()) {
            customer = customerRepository
                    .findById(UUID.fromString(id))
                    .get();
        } else {
            throw new UserNotFoundException();
        }
        return customerMapper.toCustomerDto(customer);
    }

    public void deleteCustomerById(String id) {
        if (customerRepository.existsById(UUID.fromString(id))) {
            customerRepository.deleteById(UUID.fromString(id));
        } else {
            throw new UserNotFoundException();
        }
    }

    public Customer updateCustomer(String id, Customer customer) {
        Customer currentCustomer;
        if (customerRepository.existsById(UUID.fromString(id))) {
            currentCustomer = customerRepository.getById(UUID.fromString(id));
            currentCustomer.setId(customer.getId());
            currentCustomer.setName(customer.getName());
            currentCustomer.setRating(customer.getRating());
        } else {
            throw new UserNotFoundException();
        }
        return saveCustomer(currentCustomer);
    }

}
