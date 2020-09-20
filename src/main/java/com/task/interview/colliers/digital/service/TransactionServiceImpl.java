package com.task.interview.colliers.digital.service;

import com.task.interview.colliers.digital.document.Transaction;
import com.task.interview.colliers.digital.dto.TransactionDto;
import com.task.interview.colliers.digital.service.factory.TransactionsFromDbFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final static String INCLUDE_ALL_IDS = "ALL";
    private final static String ALL_DIGITS_REGEX = "\\D+";
    private final static String IDS_SEPARATOR = ",";

    private final TransactionsFromDbFactory transactionsFromDbFactory;

    @Autowired
    public TransactionServiceImpl(TransactionsFromDbFactory transactionsFromDbFactory) {
        this.transactionsFromDbFactory = transactionsFromDbFactory;
    }

    @Override
    public List<TransactionDto> getTransactions(String accountTypeIds, String customerIds) {
        return transactionsFromDbFactory.resolve(
                resolveIds(accountTypeIds), resolveIds(customerIds)
        )
                .stream()
                .sorted(Comparator.comparing(Transaction::getAmount))
                .map(TransactionDto::mapFromTransaction)
                .collect(Collectors.toList());
    }

    private List<Long> resolveIds(String ids) {
        if (INCLUDE_ALL_IDS.equalsIgnoreCase(ids)) {
            return null;
        }
        return Arrays.stream(ids.split(IDS_SEPARATOR))
                .mapToLong(id -> Long.parseLong(id.replaceAll(ALL_DIGITS_REGEX, "")))
                .boxed()
                .collect(Collectors.toList());
    }

}
