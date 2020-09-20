package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.Customer;
import com.task.interview.colliers.digital.provider.DocumentDataProvider;
import com.task.interview.colliers.digital.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.task.interview.colliers.digital.command.datainit.constant.Constants.CUSTOMERS_FILE_PATH;
import static com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType.CUSTOMER;

@Component
@Slf4j
public class InitCustomersDocumentCommand implements InitDocumentCommand {

    private final CustomerRepository customerRepository;

    @Autowired
    public InitCustomersDocumentCommand(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing customer documents.");

        customerRepository.deleteAll();
        customerRepository.saveAll(
                getData()
        );
    }

    private List<Customer> getData() {
        return DocumentDataProvider.getBankDocumentData(CUSTOMERS_FILE_PATH, CUSTOMER)
                .values()
                .stream()
                .map(Customer::mapFromBankDocument)
                .collect(Collectors.toList());
    }

}
