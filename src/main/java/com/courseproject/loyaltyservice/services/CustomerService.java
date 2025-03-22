package com.courseproject.loyaltyservice.services;

import com.courseproject.loyaltyservice.models.dto.CustomerDTO;
import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * Create a customer.
     * The provided customer <b>MUST</b> have a customer id set.
     *
     * @param customer Customer with customer id set.
     * @return saved customer.
     */
    public Customer createCustomer(CustomerDTO customerDTO) {
        if (customerDTO.id() == null)
            return null;
        Customer customer = new Customer();
        customer.setId(customerDTO.id());

        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = getCustomerById(id);
        if (customerToUpdate == null)
            return null;
        if (customer.getId() != null)
            customerToUpdate.setId(id);
        return customerRepository.save(customerToUpdate);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
