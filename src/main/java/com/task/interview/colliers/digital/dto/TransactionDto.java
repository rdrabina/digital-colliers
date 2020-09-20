package com.task.interview.colliers.digital.dto;

import com.task.interview.colliers.digital.document.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class TransactionDto {

    private Date transactionDate;
    private Long transactionId;
    private BigDecimal transactionAmount;
    private String accountTypeName;
    private String orderPersonFirstName;
    private String orderPersonLastName;

    public static TransactionDto mapFromTransaction(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        transactionDto.setTransactionId(transaction.getId());
        transactionDto.setTransactionAmount(transaction.getAmount());
        transactionDto.setAccountTypeName(transaction.getAccountTypeName());
        transactionDto.setOrderPersonFirstName(transaction.getCustomerFirstName());
        transactionDto.setOrderPersonLastName(transaction.getCustomerLastName());

        return transactionDto;
    }

}
