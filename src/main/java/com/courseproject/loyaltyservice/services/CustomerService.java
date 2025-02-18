package com.courseproject.loyaltyservice.services;

import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Create a customer.
     * The provided customer <b>MUST</b> have a customer id set.
     * @param customer Customer with customer id set.
     * @return saved customer.
     */
    public Customer createCustomer(Customer customer) {
        if (customer.getId() == null) return null;
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(UUID id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(UUID id, Customer customer) {
        Customer customerToUpdate = getCustomerById(id);
        if (customerToUpdate == null) return null;
        if (customer.getId() != null) customerToUpdate.setId(id);
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(UUID id) {
        customerRepository.deleteById(id);
    }
}
