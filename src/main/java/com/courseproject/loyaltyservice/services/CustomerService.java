package com.courseproject.loyaltyservice.services;

import com.courseproject.loyaltyservice.models.dto.CustomerDTO;
import com.courseproject.loyaltyservice.models.Customer;
import com.courseproject.loyaltyservice.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final RedisTemplate<Long, Customer> redisTemplate;

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
        Customer customer = redisTemplate.opsForValue().get(id);
        if (customer == null) {
            log.info("Customer {} was not found in Redis", id);
            customer = customerRepository.findById(id).orElse(null);
            redisTemplate.opsForValue().set(id, customer);
        }
        return customer;
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
