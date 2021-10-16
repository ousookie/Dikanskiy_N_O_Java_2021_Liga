package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.dto.CustomerDTO;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.exception.UserNotFoundException;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Enumerates all CustomerDTO entities")
    @GetMapping
    public List<CustomerDTO> enumerate() {
        return customerService.enumerateCustomers();
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping("/new")
    public Customer save(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @ApiOperation(value = "Retrieves CustomerDTO entity by it ID")
    @GetMapping("/{id}")
    public CustomerDTO get(@PathVariable("id") String id) {
        try {
            return customerService.getCustomerById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", exception);
        }
    }

    @ApiOperation(value = "Update Customer entity by it ID")
    @PutMapping("/edit/{id}")
    public Customer update(@PathVariable("id") String id, @RequestBody Customer customer) {
        try {
            return customerService.updateCustomer(id, customer);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", exception);
        }
    }

    @ApiOperation(value = "Delete Customer entity by it ID")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        try {
            customerService.deleteCustomerById(id);
        } catch (UserNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", exception);
        }
    }

}
