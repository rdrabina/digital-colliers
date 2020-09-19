package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.AccountType;
import com.task.interview.colliers.digital.parser.CsvDataParser;
import com.task.interview.colliers.digital.repository.AccountTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class AccountTypesInitCommand implements DocumentInitCommand {

    private final static String FILE_PATH = "data/accountypes.csv";

    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypesInitCommand(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing account type documents.");

        accountTypeRepository.deleteAll();
        accountTypeRepository.saveAll(
                CsvDataParser.readAllDataAtOnce(FILE_PATH)
                        .stream()
                        .map(AccountType::mapToAccountType)
                        .collect(Collectors.toList())
        );
    }

}
