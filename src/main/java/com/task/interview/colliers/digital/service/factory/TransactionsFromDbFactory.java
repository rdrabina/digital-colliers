package com.task.interview.colliers.digital.service.factory;

import com.task.interview.colliers.digital.document.Transaction;
import com.task.interview.colliers.digital.repository.TransactionRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TransactionsFromDbFactory {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionsFromDbFactory(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> resolve(List<Long> accountTypeIds, List<Long> customerIds) {
        if (ObjectUtils.allNotNull(accountTypeIds, customerIds)) {
            return transactionRepository.findAllByAccountTypeIdInAndCustomerIdIn(accountTypeIds, customerIds);
        }
        if (Objects.nonNull(accountTypeIds)) {
            return transactionRepository.findAllByAccountTypeIdIn(accountTypeIds);
        }
        if (Objects.nonNull(customerIds)) {
            return transactionRepository.findAllByCustomerIdIn(customerIds);
        }

        return transactionRepository.findAll();
    }

}
