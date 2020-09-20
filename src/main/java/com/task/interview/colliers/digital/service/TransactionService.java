package com.task.interview.colliers.digital.service;

import com.task.interview.colliers.digital.dto.TransactionDto;

import java.util.List;

public interface TransactionService {

    List<TransactionDto> getTransactions(String accountType, String customerId);

}
