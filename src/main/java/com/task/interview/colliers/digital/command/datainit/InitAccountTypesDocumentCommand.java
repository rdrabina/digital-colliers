package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.AccountType;
import com.task.interview.colliers.digital.provider.DocumentDataProvider;
import com.task.interview.colliers.digital.repository.AccountTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.task.interview.colliers.digital.command.datainit.constant.Constants.ACCOUNT_TYPES_FILE_PATH;
import static com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType.ACCOUNT_TYPE;

@Component
@Slf4j
public class InitAccountTypesDocumentCommand implements InitDocumentCommand {
    private final AccountTypeRepository accountTypeRepository;

    @Autowired
    public InitAccountTypesDocumentCommand(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing account type documents.");

        accountTypeRepository.deleteAll();
        accountTypeRepository.saveAll(
                getData()
        );
    }

    private List<AccountType> getData() {
        return DocumentDataProvider.getBankDocumentData(ACCOUNT_TYPES_FILE_PATH, ACCOUNT_TYPE)
                .values()
                .stream()
                .map(AccountType::mapFromBankDocument)
                .collect(Collectors.toList());
    }

}
