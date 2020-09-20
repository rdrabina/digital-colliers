package com.task.interview.colliers.digital.provider.factory;

import com.task.interview.colliers.digital.command.datainit.enums.DocumentInitType;
import com.task.interview.colliers.digital.document.AccountType;
import com.task.interview.colliers.digital.document.BankDocument;
import com.task.interview.colliers.digital.document.Customer;
import com.task.interview.colliers.digital.document.Transaction;
import com.task.interview.colliers.digital.exception.DocumentMappingFunctionNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class DocumentTypeFactory {

    private static final Map<DocumentInitType, Function<String[], BankDocument>> DOCUMENT_MAPPING_FUNCTIONS;
    static {
        DOCUMENT_MAPPING_FUNCTIONS = new HashMap<>();
        DOCUMENT_MAPPING_FUNCTIONS.put(DocumentInitType.ACCOUNT_TYPE, AccountType::mapToAccountType);
        DOCUMENT_MAPPING_FUNCTIONS.put(DocumentInitType.CUSTOMER, Customer::mapToCustomer);
        DOCUMENT_MAPPING_FUNCTIONS.put(DocumentInitType.TRANSACTION, Transaction::mapToTransaction);
    }

    public static Function<String[], BankDocument> resolve(DocumentInitType documentInitType) {
        return Optional.ofNullable(DOCUMENT_MAPPING_FUNCTIONS.get(documentInitType))
                .orElseThrow(DocumentMappingFunctionNotFoundException::new);
    }

}
