package com.bank.service;

import com.bank.model.Customer;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository repository;

    @Autowired
    CustomerService(CustomerRepository aRepository) {
        this.repository = aRepository;
    }

    public List<Customer> getCustomersById(List<Integer> ids) {
        return repository.getCustomerById(ids);
    }
}
