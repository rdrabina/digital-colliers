package com.task.interview.colliers.digital.config;

import com.task.interview.colliers.digital.command.datainit.InitDocumentCommand;
import com.task.interview.colliers.digital.command.datainit.factory.DocumentInitFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

import static com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType.*;

@Component
public class DataLoader implements ApplicationRunner {

    private final DocumentInitFactory documentInitFactory;

    private final Map<Integer, InitDocumentCommand> documentInitCommandSteps = new TreeMap<>();

    @Autowired
    public DataLoader(DocumentInitFactory documentInitFactory) {
        this.documentInitFactory = documentInitFactory;
    }

    @PostConstruct
    private void initStepsMap() {
        documentInitCommandSteps.put(1, documentInitFactory.resolve(ACCOUNT_TYPE));
        documentInitCommandSteps.put(2, documentInitFactory.resolve(CUSTOMER));
        documentInitCommandSteps.put(3, documentInitFactory.resolve(TRANSACTION));
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        documentInitCommandSteps.values()
                .forEach(InitDocumentCommand::execute);
    }

}
