package com.ayna.demo.service;

import com.ayna.demo.entity.Customer;
import com.ayna.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Customer update(Customer newCustomer) {
        Optional<Customer> oldCustomer = customerRepository.findById(newCustomer.getId());

        if (oldCustomer.isPresent()) {
            Customer c = oldCustomer.get();
            c.setName(newCustomer.getName());
            c.setSurname(newCustomer.getSurname());
            c.setEmail(newCustomer.getEmail());
            c = customerRepository.save(c);
            return c;
        } else {
            newCustomer = customerRepository.save(newCustomer);
            return newCustomer;
        }
    }

    public Customer getCustomerById(Long id) throws Exception {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new Exception();
    }

    public List<Customer> sortByName() {
        return getAll().stream()
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

}

