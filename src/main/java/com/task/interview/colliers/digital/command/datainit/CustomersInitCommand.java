package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.Customer;
import com.task.interview.colliers.digital.parser.CsvDataParser;
import com.task.interview.colliers.digital.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomersInitCommand implements DocumentInitCommand {

    private final static String FILE_PATH = "data/customers.csv";

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomersInitCommand(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing customer documents.");

        customerRepository.deleteAll();
        customerRepository.saveAll(
                CsvDataParser.readAllDataAtOnce(FILE_PATH)
                        .stream()
                        .map(Customer::mapToCustomer)
                        .collect(Collectors.toList())
        );
    }

}
