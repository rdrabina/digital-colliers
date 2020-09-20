package com.task.interview.colliers.digital.command.datainit.factory;

import com.task.interview.colliers.digital.command.datainit.InitDocumentCommand;
import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType;
import com.task.interview.colliers.digital.exception.DocumentInitStepNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DocumentInitFactory {

    private final Map<DocumentInitType, InitDocumentCommand> documentInitCommands = new HashMap<>();

    private final InitDocumentCommand accountTypesInitCommand;
    private final InitDocumentCommand customersInitCommand;
    private final InitDocumentCommand transactionsInitCommand;

    @Autowired
    public DocumentInitFactory(@Qualifier("initAccountTypesDocumentCommand") InitDocumentCommand accountTypesInitCommand,
                               @Qualifier("initCustomersDocumentCommand") InitDocumentCommand customersInitCommand,
                               @Qualifier("initTransactionsDocumentCommand") InitDocumentCommand transactionsInitCommand) {
        this.accountTypesInitCommand = accountTypesInitCommand;
        this.customersInitCommand = customersInitCommand;
        this.transactionsInitCommand = transactionsInitCommand;
    }

    @PostConstruct
    private void fillCommandsMap() {
        documentInitCommands.put(DocumentInitType.ACCOUNT_TYPE, accountTypesInitCommand);
        documentInitCommands.put(DocumentInitType.CUSTOMER, customersInitCommand);
        documentInitCommands.put(DocumentInitType.TRANSACTION, transactionsInitCommand);
    }

    public InitDocumentCommand resolve(DocumentInitType documentInitType) {
        return Optional.ofNullable(
                documentInitCommands.get(documentInitType)
        ).orElseThrow(DocumentInitStepNotFoundException::new);
    }

}
