package com.task.interview.colliers.digital.service;

import com.task.interview.colliers.digital.dto.CustomerDto;
import com.task.interview.colliers.digital.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerDto::mapFromCustomer)
                .collect(Collectors.toList());
    }

}
