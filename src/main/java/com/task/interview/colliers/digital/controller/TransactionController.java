package com.task.interview.colliers.digital.controller;

import com.task.interview.colliers.digital.dto.TransactionDto;
import com.task.interview.colliers.digital.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions/")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getTransactionsWithDetails(
            @RequestParam(value = "accountType", required = false, defaultValue = "ALL") String accountType,
            @RequestParam(value = "customerId", required = false, defaultValue = "ALL") String customerId) {
        return new ResponseEntity<>(
                transactionService.getTransactions(accountType, customerId),
                HttpStatus.OK
        );
    }

}
