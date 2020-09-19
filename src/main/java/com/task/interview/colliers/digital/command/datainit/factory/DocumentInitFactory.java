package com.task.interview.colliers.digital.command.datainit.factory;

import com.task.interview.colliers.digital.command.datainit.DocumentInitCommand;
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

    private final Map<DocumentInitType, DocumentInitCommand> documentInitCommands = new HashMap<>();

    private final DocumentInitCommand accountTypesInitCommand;
    private final DocumentInitCommand customersInitCommand;
    private final DocumentInitCommand transactionsInitCommand;

    @Autowired
    public DocumentInitFactory(@Qualifier("accountTypesInitCommand") DocumentInitCommand accountTypesInitCommand,
                               @Qualifier("customersInitCommand") DocumentInitCommand customersInitCommand,
                               @Qualifier("transactionsInitCommand") DocumentInitCommand transactionsInitCommand) {
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

    public DocumentInitCommand resolve(DocumentInitType documentInitType) {
        return Optional.ofNullable(
                documentInitCommands.get(documentInitType)
        ).orElseThrow(DocumentInitStepNotFoundException::new);
    }

}
