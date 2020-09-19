package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.Transaction;
import com.task.interview.colliers.digital.parser.CsvDataParser;
import com.task.interview.colliers.digital.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@Slf4j
public class TransactionsInitCommand implements DocumentInitCommand {

    private final static String FILE_PATH = "data/transactions.csv";

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionsInitCommand(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing transaction documents.");

        transactionRepository.deleteAll();
        transactionRepository.saveAll(
                CsvDataParser.readAllDataAtOnce(FILE_PATH)
                        .stream()
                        .map(Transaction::mapToTransaction)
                        .collect(Collectors.toList())
        );
    }

}
