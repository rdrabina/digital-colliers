package com.task.interview.colliers.digital.command.datainit;

import com.task.interview.colliers.digital.document.AccountType;
import com.task.interview.colliers.digital.document.BankDocument;
import com.task.interview.colliers.digital.document.Customer;
import com.task.interview.colliers.digital.document.Transaction;
import com.task.interview.colliers.digital.provider.DocumentDataProvider;
import com.task.interview.colliers.digital.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.task.interview.colliers.digital.command.datainit.constant.Constants.*;
import static com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType.*;

@Component
@Slf4j
public class InitTransactionsDocumentCommand implements InitDocumentCommand {

    private final TransactionRepository transactionRepository;

    @Autowired
    public InitTransactionsDocumentCommand(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void execute() {
        log.info("Initializing transaction documents.");

        transactionRepository.deleteAll();
        transactionRepository.saveAll(
                getData()
        );
    }

    private List<Transaction> getData() {
        List<Transaction> transactions = DocumentDataProvider.getBankDocumentData(TRANSACTIONS_FILE_PATH, TRANSACTION)
                .values()
                .stream()
                .map(Transaction::mapFromBankDocument)
                .collect(Collectors.toList());

        setObjectFields(transactions);

        return transactions;
    }

    private void setObjectFields(List<Transaction> transactions) {
        Map<Long, BankDocument> accountTypes = DocumentDataProvider.getBankDocumentData(ACCOUNT_TYPES_FILE_PATH, ACCOUNT_TYPE);
        Map<Long, BankDocument> customers = DocumentDataProvider.getBankDocumentData(CUSTOMERS_FILE_PATH, CUSTOMER);

        for (Transaction transaction : transactions) {
            transaction.setAccountType(
                    (AccountType) accountTypes.getOrDefault(transaction.getAccountTypeId(), null)
            );
            transaction.setCustomer(
                    (Customer) customers.getOrDefault(transaction.getCustomerId(), null)
            );
        }

    }

}
