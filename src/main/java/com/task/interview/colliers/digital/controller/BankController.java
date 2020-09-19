package com.task.interview.colliers.digital.controller;

import com.task.interview.colliers.digital.dto.CustomerDto;
import com.task.interview.colliers.digital.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BankController {

    private final CustomerService customerService;

    @Autowired
    public BankController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("customers")
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK
        );
    }
}
