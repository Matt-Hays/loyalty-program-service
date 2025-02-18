package com.courseproject.loyaltyservice.controllers;

import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.services.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable UUID id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody @Valid Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PatchMapping("/{id}")
    public Customer updateCustomer(@PathVariable UUID id, @RequestBody @Valid Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.deleteCustomer(id);
    }
}
